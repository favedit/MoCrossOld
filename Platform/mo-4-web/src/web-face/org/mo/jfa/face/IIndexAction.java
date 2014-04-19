package org.mo.jfa.face;

import org.mo.web.core.container.AContainer;

public interface IIndexAction
{

   String construct(@AContainer(name = "page", clear = true) FIndexPage page);

   String delete(@AContainer(name = "page") FIndexPage page);

   String insert(@AContainer(name = "page") FIndexPage page);

   String update(@AContainer(name = "page") FIndexPage page);
}
