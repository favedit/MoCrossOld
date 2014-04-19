using System;
using System.Collections.Generic;
using System.Text;
using System.DirectoryServices;
using MO.Common.Lang;

namespace MO.Core.Window.Services {

   public class FAdDirectory : FAdObject {

      private ILogger _logger = RLogger.Find(typeof(FAdDirectory));

      private FAdOrganizationalUnits _organizationalUnits;

      private FAdGroups _groups;

      private FAdUsers _users;

      public FAdDirectory() {
      }

      public FAdDirectory(FAdConnection connection)
         : base(connection) {
      }

      public FAdOrganizationalUnits OrganizationalUnits {
         get { return GetOrganizationalUnits(false); }
      }

      public FAdGroups Groups {
         get { return GetGroups(false); }
      }

      public FAdUsers Users {
         get {return GetUsers(false);}
      }

      public FAdOrganizationalUnits GetOrganizationalUnits(bool refresh) {
         if (!refresh && _organizationalUnits != null) {
            return _organizationalUnits;
         }
         // Create
         _organizationalUnits = new FAdOrganizationalUnits();
         foreach (FAdObject adObject in GetChildren(refresh)) {
            if (adObject is FAdOrganizationalUnit) {
               _organizationalUnits[adObject.Entry.NativeGuid] = (FAdOrganizationalUnit)adObject;
            }
         }
         return _organizationalUnits;
      }

      public FAdGroups GetGroups(bool refresh) {
         if (!refresh && _groups != null) {
            return _groups;
         }
         // Create
         _groups = new FAdGroups();
         foreach (FAdObject adObject in GetChildren(refresh)) {
            if (adObject is FAdGroup) {
               _groups[adObject.Entry.NativeGuid] = (FAdGroup)adObject;
            }
         }
         return _groups;
      }

      public FAdUsers GetUsers(bool refresh) {
         if (!refresh && _users != null) {
            return _users;
         }
         // Create
         _users = new FAdUsers();
         foreach (FAdObject adObject in GetChildren(refresh)) {
            if (adObject is FAdUser) {
               _users[adObject.Entry.NativeGuid] = (FAdUser)adObject;
            }
         }
         return _users;
      }

   }

}
