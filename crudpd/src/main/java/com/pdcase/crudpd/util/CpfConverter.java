package com.pdcase.crudpd.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
* <b>Converter para CPF.</b>
*
* @author Dilnei Cunha
*/
@FacesConverter("cpfConverter")
public class CpfConverter implements Converter {

    /**
    * <b>Método que remove a mascara do CPF.</b>
    *
    * @param facesContext
    * @param uIcomponent
    * @param cpf
    * @return Object
    */
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uIcomponent, String cpf) {
        if (cpf.trim().equals("")) {
            return null;
        } else {
           cpf = cpf.replace("-", "");
           cpf = cpf.replace(".", "");
           return cpf;
       }
    }

    
    public static String formatCpf(String cpf)
    {
         if (cpf != null && cpf.length() == 11)
              cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);

         return cpf;
    }
    
    /**
    * <b>Método que retorna a String do CPF.</b>
    *
    * @param facesContext
    * @param uIcomponent
    * @param object
    * @return String
    */
    @Override
    public String getAsString(FacesContext facesContext, UIComponent uIcomponent, Object object) {
    	 /*
         * Irá converter CPF não formatado para um com pontos e traço.
         * Ex.: 35524519887 torna-se 355.245.198-87.
         */
         String cpf= (String) object;
         if (cpf != null && cpf.length() == 11)
              cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);

         return cpf;
    }
}
