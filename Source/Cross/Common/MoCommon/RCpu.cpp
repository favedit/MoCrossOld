#ifdef _MO_LINUX
#include <unistd.h>
#include <fcntl.h>
#include <sys/resource.h>
#include <sys/times.h>
#endif
#include "MoCmSystem.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>获得CPU循环数(指令周期)。</T>
//
// @return 指令周期数
//============================================================
TUint64 RCpu::CurrentCycleCount(){
   TUint32 dt_eax = 0;
   TUint32 dt_edx = 0;
   //............................................................
#ifdef _MO_WINDOWS
#ifdef _MO_X64
   //__asm {
   //   rdtsc
   //   ret
   //}
   // MO_STATIC_FATAL(TC("Unsupport method."));
   // __asm _emit 0x0F
   // __asm _emit 0x31
#else
   __asm _emit 0x0F
   __asm _emit 0x31
#endif
   //__asm _emit mov dt_eax eax
   //__asm _emit mov dt_edx edx
#endif
   //............................................................
#ifdef _MO_LINUX
   __asm__ __volatile__(".byte 0x0F");
   __asm__ __volatile__(".byte 0x31");
   __asm__ __volatile__("movl %%eax, %0" : "=r"(dt_eax) : : "%eax","%edx");
   __asm__ __volatile__("movl %%edx, %0" : "=r"(dt_edx) : : "%eax","%edx");
#endif
   TUint64 cycle = ((TUint64)dt_edx << 32) | (TUint64)dt_eax;
   return cycle;
}

//============================================================
//取得线程当前所耗用的CPU时间。
//============================================================
TUint64 RCpu::GetTime(TThreadProcessId pid){
#ifdef _MO_LINUX
   if(pid <= 0) {
      return 0;
   }
   int fd;
   char fn[128];
   snprintf(fn, sizeof(fn), "/proc/self/task/%d/stat", pid);
   if((fd = open(fn, O_RDONLY)) < 0) {
      perror("open");
      return 0;
   }
   int len = 0;
   char buf[1024];
   for(int i = 0; (i = read(fd, &buf[len], 1024 - len)) > 0;) {
      len += i;
   }
   buf[len] = 0;
   close(fd);

   TUint64 ct = 0;
   int i = 0;
   char *p = buf;
   for(; i < 13; ++p) {
      if(*p == ' ') {
         ++i;
      }
   }
   //得到用户模式所用时间。
   ct = strtoll(p, &p, 10);
   //加上内核模式所用时间。
   ct += strtoll(++p, 0, 10);
   return ct;
#else
   return 0;
#endif
}

//============================================================
//计算线程在两次取样点之间的CPU占用率。
//============================================================
double RCpu::GetRate(TUint64 proc, TUint64 proc2, TUint64 real, TUint64 real2){
#ifdef _MO_LINUX
   TUint64 tmp = (real2 - real) * sysconf(_SC_CLK_TCK);
   return tmp > 0 ? (double)(proc2 - proc) / tmp  * MoCpuFrequency : 0;
#else
   return 0;
#endif
}

//============================================================
TFloat RCpu::GetLoadFactor(){
#ifdef _MO_LINUX
   struct tms tt;
   times(&tt);
   TUint64 proc = tt.tms_utime + tt.tms_stime;
   TUint64 cpu = CurrentCycleCount();

   usleep(1000);

   times(&tt);
   TUint64 proc2 = tt.tms_utime + tt.tms_stime;
   TUint64 cpu2 = CurrentCycleCount();
   TFloat rate = (TFloat)GetRate(proc, proc2, cpu, cpu2);
   return rate > 1 ? 1 : rate;
#else
   return 0;
#endif
}

//============================================================
TInt RCpu::GetCpuCount(){
#ifdef _MO_LINUX
   int fd;
   if((fd = open("/proc/cpuinfo", O_RDONLY)) < 0) {
      perror("open");
      return -1;
   }

   char buf[81920];
   TInt len = 0;
   for(int i = 0; (i = read(fd, &buf[len], sizeof(buf)-len)) > 0;)
      len += i;
   close(fd);

   buf[len] = 0;
        TInt cpu = 0;
   for(char *p = buf; (p = strstr(p, "processor")); p += 9)
      ++cpu;

   return cpu;
#else
        return 0;
#endif
}

//============================================================
//计算CPU频率。
//============================================================
TInt64 RCpu::GetCpuRate(){
#ifdef _MO_LINUX
   //打开文件
   int fd;
   if((fd = open("/proc/cpuinfo", O_RDONLY)) < 0) {
      perror("open");
      return -1;
   }
   //加载
   char buf[1024];
   double fra = 0;
   int len = 0;
   for(int i = 0; len < (signed)sizeof(buf) && (i = read(fd, &buf[len], sizeof(buf)-len)) > 0;) {
      len += i;
   }
   close(fd);
   //查找Cpu频率并返回
   buf[len] = 0;
   char *p;
   if((p = strstr(buf, "model name"))) {
      if((p = strchr(p, '@'))) {
         p += 2;
         fra = strtod(p, &p);
         if(*p == 'G')
            fra *= 1000000000UL;
      }
   }
   return (TUint64)fra;
#else
   return 0;
#endif
}

//============================================================
//计算当前CPU频率。
//============================================================
TInt64 RCpu::GetCpuHz(){
#ifdef _MO_LINUX
   //打开文件
   int fd;
   if((fd = open("/proc/cpuinfo", O_RDONLY)) < 0) {
      perror("open");
      return -1;
   }
   //加载
   char buf[1024];
   double fra = 0;
   int len = 0;
   for(int i = 0; len < (signed)sizeof(buf) &&
         (i = read(fd, &buf[len], sizeof(buf)-len)) > 0;) {
      len += i;
   }
   close(fd);
   //查找Cpu MHz并返回
   buf[len] = 0;
   char *p;
   if((p = strstr(buf, "cpu MHz"))) {
      if((p = strchr(p, ':'))) {
         p += 2;
         fra = strtod(p, &p) * 1000000UL;
      }
   }
   return (TUint64)fra;
#else
   return 0;
#endif
}

MO_NAMESPACE_END
