using System;
using MO.Common.Lang;

namespace MO.Common.Content {

   public class FCsvHead : IName<String>, IDump {

      public const string HEAD_TYPE = "Head";

      public const string PTY_ID = "id";

      public const string PTY_TYPE = "type";

      public const string PTY_NAME = "name";

      public const string PTY_SIZE = "size";

      public const string PTY_DESCRIPTION = "description";

      public const string PTY_KEY = "key";

      private static ILogger _logger = RLogger.Find(typeof(FCsvHead));

      //private FXmlNode _config;

      private int _id;

      private string _type;

      private string _name;

      private int _size;

      //private FCsvValidators _validators;

      private FCsvFormator _readFormator;

      private FCsvFormator _writeFormator;

      private string _description;

      private bool _key;

      public FCsvHead() {
      }

      //public FXmlNode Config {
      //   get { return _config; }
      //}

      public int Id {
         get { return _id; }
         set { _id = value; }
      }

      public string Type {
         get { return _type; }
         set { _type = value; }
      }

      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      public int Size {
         get { return _size; }
         set { _size = value; }
      }

      public bool Key {
         get { return _key; }
         set { _key = value; }
      }

      public string Description {
         get { return _description; }
         set { _description = value; }
      }

      public FCsvFormator ReadFormator {
         get { return _readFormator; }
         set { _readFormator = value; }
      }

      public FCsvFormator WriteFormator {
         get { return _writeFormator; }
         set { _writeFormator = value; }
      }

      //public FCsvValidators Validators {
      //   get { return _validators; }
      //}

      public void LoadConfig(FXmlNode config) {
         /*_config = config;
         // Load values
         if (config.Contains(PTY_ID)) {
            _id = config.GetInteger(PTY_ID);
         }
         if (config.Contains(PTY_TYPE)) {
            _type = config[PTY_TYPE];
         }
         if (config.Contains(PTY_NAME)) {
            _name = config[PTY_NAME];
         }
         if (config.Contains(PTY_SIZE)) {
            _size = config.GetInteger(PTY_SIZE);
         }
         if (config.Contains(PTY_DESCRIPTION)) {
            _description = config[PTY_DESCRIPTION];
         }
         _key = config.GetBoolean(PTY_KEY);
         // Load children
         if (config.HasNode) {
            if (config.ContainsNode(FCsvValidator.TAG)) {
               _validators = new FCsvValidators();
            }
            foreach (FXmlNode node in config.Nodes) {
               if (node.IsName(FCsvValidator.TAG)) {
                  // Load Validator
                  FCsvValidator validator = new FCsvValidator();
                  validator.LoadConfig(node);
                  _validators.Push(validator);
               } else if (node.IsName(FCsvFormator.TAG)) {
                  // Formator
                  FCsvFormator formator = new FCsvFormator();
                  formator.LoadConfig(node);
                  if (formator.Type == ECsvWorkType.Read) {
                     _readFormator = formator;
                  } else if (formator.Type == ECsvWorkType.Write) {
                     _writeFormator = formator;
                  }
               }
            }
         }
         // Dump
         if (_logger.DebugAble) {
            _logger.Debug(this, "LoadConfig", "Load head {0}", Dump());
         }*/
      }

      public bool HasValidator(ECsvWorkType type) {
         /*if (_validators != null) {
            foreach (FCsvValidator validator in _validators) {
               if (type == validator.Type) {
                  return true;
               }
            }
         }*/
         return false;
      }

      /*public bool Check(ECsvWorkType type, FValidatorParameters parameters) {
         if (_validators != null) {
            foreach (FCsvValidator validator in _validators) {
               if (validator.Type == type) {
                  if (!validator.Check(parameters)) {
                     return false;
                  }
               }
            }
         }
         return true;
      }

      public bool Format(ECsvWorkType type, FFormatorParameters parameters) {
         if (type == ECsvWorkType.Read && _readFormator != null) {
            return _readFormator.Format(parameters);
         } else if (type == ECsvWorkType.Write && _writeFormator != null) {
            return _writeFormator.Format(parameters);
         }
         return true;
      }*/

      #region IDump members

      public FDumpInfo Dump() {
         return Dump(new FDumpInfo(this));
      }

      public virtual FDumpInfo Dump(FDumpInfo info) {
         RDump.StartDump(info);
         info.Append("{ ");
         info.AppendProperty("id", _id, true);
         info.AppendProperty("name", _name, true);
         info.AppendProperty("size", _size, true);
         info.AppendProperty("description", _description, false);
         info.Append(" }");
         return info;
      }

      #endregion
   }

}
