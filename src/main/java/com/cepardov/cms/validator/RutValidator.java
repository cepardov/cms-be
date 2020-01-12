package com.cepardov.cms.validator;

import com.cepardov.cms.util.RutUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RutValidator implements ConstraintValidator<Rut, String> {

    private static final Logger LOGGER = LogManager.getLogger(RutValidator.class.getName());

    @Override
    public void initialize(Rut constraintAnnotation) {

    }

    @Override
    public boolean isValid(String rutIn, ConstraintValidatorContext constraintValidatorContext) {
        if(rutIn == null){
            return true;
        }

        if(rutIn.equals("0000000000")) {
            return true;
        }

        boolean validacion = false;

        RutUtil rutUtil = new RutUtil();

        String rut = rutUtil.getRutToString(rutIn)+"-"+rutUtil.getDv(rutIn);

        Pattern pattern = Pattern.compile("^[0-9]+-[0-9kK]{1}$");
        Matcher matcher = pattern.matcher(rut);
        if ( matcher.matches() == false ){
            LOGGER.warn("RUT Invalido by matcher");
            return false;
        }
        try {
            rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (NumberFormatException e) {
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return validacion;
    }
}
