#include "Mo3dsDefine.h"
#include "Mo3dsExporter.h"

MO_NAMESPACE_BEGIN;

//============================================================
TCharC* R3dsClass::GetSuperClassName(SClass_ID classId){
   switch(classId){
      case BASENODE_CLASS_ID:
         return TC("BASENODE");
      case GEOMOBJECT_CLASS_ID:
         return TC("GEOMOBJECT");
      case CAMERA_CLASS_ID:
         return TC("CAMERA");
      case LIGHT_CLASS_ID:
         return TC("LIGHT");
      case SHAPE_CLASS_ID:
         return TC("SHAPE");
      case HELPER_CLASS_ID:
         return TC("HELPER");
      case SYSTEM_CLASS_ID:
         return TC("SYSTEM");
      case REF_MAKER_CLASS_ID:
         return TC("REF_MAKER");
      case REF_TARGET_CLASS_ID:
         return TC("REF_TARGET");
      case OSM_CLASS_ID:
         return TC("OSM");
      case WSM_CLASS_ID:
         return TC("WSM");
      case WSM_OBJECT_CLASS_ID:
         return TC("WSM_OBJECT");
      case SCENE_IMPORT_CLASS_ID:
      case SCENE_EXPORT_CLASS_ID:
      case BMM_STORAGE_CLASS_ID:
      case BMM_FILTER_CLASS_ID:
      case BMM_IO_CLASS_ID:
      case BMM_DITHER_CLASS_ID:
      case BMM_COLORCUT_CLASS_ID:
      case USERDATATYPE_CLASS_ID:
      case MATERIAL_CLASS_ID:
      case TEXMAP_CLASS_ID:
      case UVGEN_CLASS_ID:
      case XYZGEN_CLASS_ID:
      case TEXOUTPUT_CLASS_ID:
      case SOUNDOBJ_CLASS_ID:
      case FLT_CLASS_ID:
      case RENDERER_CLASS_ID:
      case BEZFONT_LOADER_CLASS_ID:
      case ATMOSPHERIC_CLASS_ID:
      case UTILITY_CLASS_ID:
      case TRACKVIEW_UTILITY_CLASS_ID:
      case MOT_CAP_DEV_CLASS_ID:
      case MOT_CAP_DEVBINDING_CLASS_ID:
      case OSNAP_CLASS_ID:
      case TEXMAP_CONTAINER_CLASS_ID:
      case RENDER_EFFECT_CLASS_ID:
      case FILTER_KERNEL_CLASS_ID:
      case SHADER_CLASS_ID:
      case COLPICK_CLASS_ID:
      case SHADOW_TYPE_CLASS_ID:
      case GUP_CLASS_ID:
      case LAYER_CLASS_ID:
      case SCHEMATICVIEW_UTILITY_CLASS_ID:
      case SAMPLER_CLASS_ID:
      case IK_SOLVER_CLASS_ID:
      case RENDER_ELEMENT_CLASS_ID:
      case BAKE_ELEMENT_CLASS_ID:
      case CUST_ATTRIB_CLASS_ID:
      case RADIOSITY_CLASS_ID:
      case TONE_OPERATOR_CLASS_ID:
      case MPASS_CAM_EFFECT_CLASS_ID:
      case MR_SHADER_CLASS_ID_DEFUNCT:
         return TC("Unknown");
   }
   return NULL;
}

//============================================================
TCharC* R3dsClass::GetClassName(Class_ID classId){
   switch(classId.PartA()){
      case EDITTRIOBJ_CLASS_ID:
         return TC("EDITTRIOBJ");
      case TRIOBJ_CLASS_ID:
         return TC("TRIOBJ");
      case BOXOBJ_CLASS_ID:
         return TC("BOXOBJ");
      case SPHERE_CLASS_ID:
         return TC("SPHERE");
      case CYLINDER_CLASS_ID:
         return TC("CYLINDER");
      case DUMMY_CLASS_ID:
         return TC("DUMMY");
      default:
         return TC("Unknown");
   }
   return NULL;
}

//============================================================
TCharC* R3dsClass::GetControlType(IGameControl::MaxControlType type){
   switch(type){
		case IGameControl::IGAME_MAXSTD:
         return TC("MaxStd");
      case IGameControl::IGAME_BIPED:
         return TC("Biped");
		case IGameControl::IGAME_EULER:
         return TC("Euler");
		case IGameControl::IGAME_ROT_CONSTRAINT:
         return TC("RotConstraint");
		case IGameControl::IGAME_POS_CONSTRAINT:
         return TC("PosConstraint");
		case IGameControl::IGAME_LINK_CONSTRAINT:
         return TC("ConConstraint");
		case IGameControl::IGAME_LIST:
         return TC("List");
		case IGameControl::IGAME_INDEPENDENT_POS:
         return TC("IndependentPos");
		case IGameControl::IGAME_MASTER:
         return TC("Master");
   }
   return TC("Unknown");
}

MO_NAMESPACE_END;
