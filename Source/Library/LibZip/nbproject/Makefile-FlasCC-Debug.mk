#
# Generated Makefile - do not edit!
#
# Edit the Makefile in the project folder instead (../Makefile). Each target
# has a -pre and a -post target defined where you can add customized code.
#
# This makefile implements configuration specific macros and targets.


# Environment
MKDIR=mkdir
CP=cp
GREP=grep
NM=nm
CCADMIN=CCadmin
RANLIB=ranlib
CC=gcc
CCC=g++
CXX=g++
FC=gfortran
AS=as

# Macros
CND_PLATFORM=FlasCC-Windows
CND_DLIB_EXT=dll
CND_CONF=FlasCC-Debug
CND_DISTDIR=dist
CND_BUILDDIR=build

# Include project Makefile
include Makefile

# Object Directory
OBJECTDIR=${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}

# Object Files
OBJECTFILES= \
	${OBJECTDIR}/Alloc.o \
	${OBJECTDIR}/Bra.o \
	${OBJECTDIR}/LzFind.o \
	${OBJECTDIR}/Lzma2Dec.o \
	${OBJECTDIR}/Lzma86Dec.o \
	${OBJECTDIR}/LzmaDec.o \
	${OBJECTDIR}/LzmaLib.o


# C Compiler Flags
CFLAGS=-emit-llvm

# CC Compiler Flags
CCFLAGS=-jvmopt=-Xmx2G -emit-llvm
CXXFLAGS=-jvmopt=-Xmx2G -emit-llvm

# Fortran Compiler Flags
FFLAGS=

# Assembler Flags
ASFLAGS=

# Link Libraries and Options
LDLIBSOPTIONS=

# Build Targets
.build-conf: ${BUILD_SUBPROJECTS}
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmolzma.a

${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmolzma.a: ${OBJECTFILES}
	${MKDIR} -p ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}
	${RM} ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmolzma.a
	${AR} -rv ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmolzma.a ${OBJECTFILES} 
	$(RANLIB) ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmolzma.a

${OBJECTDIR}/Alloc.o: Alloc.c 
	${MKDIR} -p ${OBJECTDIR}
	$(COMPILE.c) -g -Wall -D_MO_DEBUG -D_MO_FLASC -I../../../Studio/SDK/flascc_1.0.0/sdk/usr/include -o ${OBJECTDIR}/Alloc.o Alloc.c

${OBJECTDIR}/Bra.o: Bra.c 
	${MKDIR} -p ${OBJECTDIR}
	$(COMPILE.c) -g -Wall -D_MO_DEBUG -D_MO_FLASC -I../../../Studio/SDK/flascc_1.0.0/sdk/usr/include -o ${OBJECTDIR}/Bra.o Bra.c

${OBJECTDIR}/LzFind.o: LzFind.c 
	${MKDIR} -p ${OBJECTDIR}
	$(COMPILE.c) -g -Wall -D_MO_DEBUG -D_MO_FLASC -I../../../Studio/SDK/flascc_1.0.0/sdk/usr/include -o ${OBJECTDIR}/LzFind.o LzFind.c

${OBJECTDIR}/Lzma2Dec.o: Lzma2Dec.c 
	${MKDIR} -p ${OBJECTDIR}
	$(COMPILE.c) -g -Wall -D_MO_DEBUG -D_MO_FLASC -I../../../Studio/SDK/flascc_1.0.0/sdk/usr/include -o ${OBJECTDIR}/Lzma2Dec.o Lzma2Dec.c

${OBJECTDIR}/Lzma86Dec.o: Lzma86Dec.c 
	${MKDIR} -p ${OBJECTDIR}
	$(COMPILE.c) -g -Wall -D_MO_DEBUG -D_MO_FLASC -I../../../Studio/SDK/flascc_1.0.0/sdk/usr/include -o ${OBJECTDIR}/Lzma86Dec.o Lzma86Dec.c

${OBJECTDIR}/LzmaDec.o: LzmaDec.c 
	${MKDIR} -p ${OBJECTDIR}
	$(COMPILE.c) -g -Wall -D_MO_DEBUG -D_MO_FLASC -I../../../Studio/SDK/flascc_1.0.0/sdk/usr/include -o ${OBJECTDIR}/LzmaDec.o LzmaDec.c

${OBJECTDIR}/LzmaLib.o: LzmaLib.c 
	${MKDIR} -p ${OBJECTDIR}
	$(COMPILE.c) -g -Wall -D_MO_DEBUG -D_MO_FLASC -I../../../Studio/SDK/flascc_1.0.0/sdk/usr/include -o ${OBJECTDIR}/LzmaLib.o LzmaLib.c

# Subprojects
.build-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r ${CND_BUILDDIR}/${CND_CONF}
	${RM} ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/libmolzma.a

# Subprojects
.clean-subprojects:
