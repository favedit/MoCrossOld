using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;

namespace MObj.Windows.Schedule {

   public class FScheduleConsole : IScheduleConsole {

      private FSchedules _schedules;

      private IScheduleFactory _factory;

      private FScheduleTimer _timer;

      public FScheduleConsole() {
         _schedules = new FSchedules(this);
         _factory = new FScheduleFactory();
         _timer = new FScheduleTimer(this);
      }

      public IScheduleFactory Factory {
         get { return _factory; }
      }

      public FScheduleTimer Timer {
         get { return _timer; }
      }

      public FSchedules Schedules {
         get { return _schedules; }
      }

      public void LoadFile(string filename) {
         Clear();
         FXmlDocument xdoc = new FXmlDocument(filename);
         xdoc.LoadFile(filename);
         LoadConfig(xdoc.Root);
      }

      public void LoadConfig(FXmlNode config) {
         if (config.HasNode()) {
            foreach (FXmlNode node in config.Nodes) {
               if (node.IsName(FSchedules.NAME)) {
                  // Load schedule
                  _schedules.LoadConfig(node);
               }
            }
         }
      }

      public void SaveFile(string filename) {
         FXmlDocument xdoc = new FXmlDocument();
         SaveConfig(xdoc.Root);
         xdoc.SaveFile(filename);
      }

      public void SaveConfig(FXmlNode config) {
         _schedules.SaveConfig(config.CreateNode(FSchedules.NAME));
      }

      public void Clear() {
         _schedules.Clear();
      }

   }

}
