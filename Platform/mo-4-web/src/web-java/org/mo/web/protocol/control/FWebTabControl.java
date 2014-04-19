package org.mo.web.protocol.control;

public class FWebTabControl
{

   //   public static final String SHEET_NAME = "name";
   //
   //   public static final String SHEET_LABEL = "label";
   //
   //   public static final String SHEET_ICON = "icon";
   //
   //   public static final String SHEET_HINT = "hint";
   //
   //   public static final String SHEET_ACTION = "action";
   //
   //   private String m_sName = null;
   //
   //   public String name(){
   //      return m_sName;
   //   }
   //
   //   public void setName(String sName){
   //      m_sName = sName;
   //   }
   //
   //   private String m_sCaption = "";
   //
   //   private String m_sTargetForm = "";
   //
   //   private String m_sTargetJSP = "";
   //
   //   private int m_sActivePage = 0;
   //
   //   private FWebSheets m_oSheets = new FWebSheets();
   //
   //   // ------------------------------------------------------------
   //   public FWebTabControl(){
   //   }
   //
   //   // ============================================================
   //   public String caption(){
   //      return m_sCaption;
   //   }
   //
   //   public boolean setCaption(String sValue){
   //      m_sCaption = sValue;
   //      return true;
   //   }
   //
   //   // ============================================================
   //   private String m_sPageIndex = null;
   //
   //   public String pageIndex(){
   //      return m_sPageIndex;
   //   }
   //
   //   public void setPageIndex(String sValue){
   //      m_sPageIndex = sValue;
   //   }
   //
   //   // ============================================================
   //   public String targetForm(){
   //      return m_sTargetForm;
   //   }
   //
   //   public boolean setTargetForm(String sValue){
   //      m_sTargetForm = sValue;
   //      return true;
   //   }
   //
   //   // ============================================================
   //   public String targetJSP(){
   //      return m_sTargetJSP;
   //   }
   //
   //   public boolean setTargetJSP(String sValue){
   //      m_sTargetJSP = sValue;
   //      return true;
   //   }
   //
   //   // ============================================================
   //   public int activePage(){
   //      return m_sActivePage;
   //   }
   //
   //   public boolean setActivePage(int nValue){
   //      if(nValue >= 0 && nValue < m_oSheets.size()){
   //         m_sActivePage = nValue;
   //      }else{
   //         m_sActivePage = 0;
   //      }
   //      return true;
   //   }
   //
   //   // ============================================================
   //   public FWebSheets sheets(){
   //      return m_oSheets;
   //   }
   //
   //   // ============================================================
   //   public FWebSheet sheet(int nIndex){
   //      return m_oSheets.get(nIndex);
   //   }
   //
   //   // ============================================================
   //   public String innerHTML(){
   //      return "<DIV id='id_" + name() + "'></DIV>\n";
   //   }
   //
   //   // ============================================================
   //   private String m_sLinkHTMLForm = "parent.frames.frmToolBar";
   //
   //   public String linkHTMLForm(){
   //      return m_sLinkHTMLForm;
   //   }
   //
   //   public boolean setLinkHTMLForm(String sValue){
   //      m_sLinkHTMLForm = sValue;
   //      return true;
   //   }
   //
   //   // ============================================================
   //   private String m_sOnSheetClick = null;
   //
   //   public String onSheetClick(){
   //      return m_sOnSheetClick;
   //   }
   //
   //   public boolean setOnSheetClick(String sValue){
   //      m_sOnSheetClick = sValue;
   //      return true;
   //   }
   //
   //   // ============================================================
   //   private String m_sLinkHTMLObject = "oPageCtl";
   //
   //   public String linkHTMLObject(){
   //      return m_sLinkHTMLObject;
   //   }
   //
   //   public boolean setLinkHTMLObject(String sValue){
   //      m_sLinkHTMLObject = sValue;
   //      return true;
   //   }
   //
   //   // ============================================================
   //   public String makeRefreshScript(){
   //      int nCount = m_oSheets.size();
   //      FWebSheet oTabSheet = null;
   //      StringBuffer oScript = new StringBuffer();
   //      oScript.append("{name} = obj.create('LH.PageCtl');\n" + "{name}.linkHTML = id_{name};\n" + "{name}.targetForm = \"" + m_sTargetForm + "\";\n" + "{name}.targetJSP = \"" + m_sTargetJSP + "\";\n");
   //      for(int n = 0; n < nCount; n++){
   //         oTabSheet = (FWebSheet) m_oSheets.get(n);
   //         oScript.append("{name}.newSheet(" + "\"" + oTabSheet.name() + "\"," + "\"" + oTabSheet.label() + "\"," + "\"" + oTabSheet.icon() + "\"," + "\"{context_path}" + oTabSheet.action() + "\","
   //               + "\"" + oTabSheet.hint() + "\");\n");
   //      }
   //      if(!RString.isEmpty(onSheetClick())){
   //         String sOnSheetClick = onSheetClick();
   //         if(sOnSheetClick.indexOf("(") >= 0){
   //            sOnSheetClick = sOnSheetClick.substring(0, sOnSheetClick.indexOf("("));
   //         }
   //         oScript.append("{name}.onSheetClick = " + sOnSheetClick + ";\n");
   //      }
   //      return oScript.toString();
   //   }
   //
   //   // ============================================================
   //   public String makeOnLoadScript()
   //         throws FException{
   //      if(!RString.isEmpty(pageIndex())){
   //         setActivePage(m_oSheets.indexOf(pageIndex()));
   //      }
   //      //      return "{name}.selectPage(" + activePage() + ");\n"
   //      //            + "frmConsole.target = \"" + m_sTargetForm + "\";\n"
   //      //            + "frmConsole.action = \"{context_path}"
   //      //            + ((FStdSheet) m_oSheets.value(activePage())).getLinkAction()
   //      //            + "\";\n" + "frmConsole.submit();\n";
   //      return "{name}.selectPage(" + activePage() + ");\n" + "frmConsole.target = \"" + m_sTargetForm + "\";\n" + "frmConsole.action = \"{context_path}" + m_oSheets.get(0).action() + "\";\n"
   //            + "frmConsole.submit();\n";
   //   }
}
