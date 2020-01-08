package com.cepardov.cms.util;

public class RutUtil {

    //private static final Logger LOGGER = LogManager.getLogger(RutUtil.class.getName());

    /**
     * Convierte un RUT con puntos, guión y digito verificador un RUT sin digito.
     * @param rutIn
     * @return RUT Sin digito verificador
     */
    public String getRut(String rutIn){
        String rut = rutIn.replace(".", "");
        if(rut != null && !rut.isEmpty()){
            String[] rutdv;
            rutdv = rut.split("-");
            return rutdv[0];
        }
        return null;
    }

    /**
     *
     * @param rutIn
     * @return RUT sin puntos ni guion con digito verificador
     */
    public Double getRutToDouble(String rutIn){
        if(!rutIn.isEmpty() && null != rutIn && rutIn.length() > 1){
            String rut = rutIn.replace(".", "").toUpperCase();
            Boolean isSeparator = rut.contains("-");
            if(isSeparator){
                String[] rutdv;
                rutdv = rut.split("-");
                return Double.parseDouble(rutdv[0]);
            } else {
                String dv = rut.substring(0, rut.length() -1 );
                return Double.parseDouble(dv);
            }
        }
        return null;
    }

    public String getRutToString(String rutIn){
        if(!rutIn.isEmpty() && null != rutIn){
            String rut = rutIn.replace(".", "").toUpperCase();
            Boolean isSeparator = rut.contains("-");
            if(isSeparator){
                String[] rutdv;
                rutdv = rut.split("-");
                return rutdv[0];
            } else {
                String rutNoDv = rut.substring(0, rut.length() -1 );
                return rutNoDv;
            }

        }
        return null;
    }

    /**
     * Extrae digito verificador de un RUT
     * @param rutIn
     * @return String Digito verificador
     */
    public String getDv(String rutIn){
        if(!rutIn.isEmpty() && null != rutIn){
            String rut = rutIn.replace(".", "").toUpperCase();
            Boolean isSeparator = rut.contains("-");
            if(isSeparator){
                String[] rutdv;
                rutdv = rut.split("-");
                return rutdv[1];
            } else {
                String dv = rut.substring(rut.length() - 1);
                return dv;
            }

        }
        return null;
    }
}
