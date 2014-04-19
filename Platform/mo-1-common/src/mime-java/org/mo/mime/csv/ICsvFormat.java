package org.mo.mime.csv;

public interface ICsvFormat
{
   String delimiter();

   String dataClose();

   String header();

   String footer();

   String footerCheck();

   String footerChar();

   String impUniqChk();

   String footerOpt();

   String passwdCrypt();
}
