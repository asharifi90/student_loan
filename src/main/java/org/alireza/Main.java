package org.alireza;

import org.alireza.model.Enum.UniversityType;
import org.alireza.model.University;
import org.alireza.util.ApplicationContext;

public class Main {
    public static void main(String[] args) {

        University university = new University("Tehran", "Tehran", UniversityType.DOWLATI_ROOZANEH,null);
        ApplicationContext.getUniversityService().saveOrUpdate(university);

    }
}
