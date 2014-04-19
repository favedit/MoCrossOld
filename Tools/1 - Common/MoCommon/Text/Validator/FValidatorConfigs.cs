using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;

namespace MO.Common.Text.Validator {

   public class FValidatorConfigs : FDictionary<FValidatorConfig> {

      public const string TAG = "ValidatorConfigs";

      public void LoadConfig(FXmlNode config) {
         if(config.HasNode){
            foreach(FXmlNode node in config.Nodes){
               if (node.IsName(FValidatorConfig.TAG)) {
                  FValidatorConfig validatorConfig = new FValidatorConfig();
                  validatorConfig.LoadConfig(node);
                  Set(validatorConfig.Name, validatorConfig);
               }
            }
         }
      }

   }

}
