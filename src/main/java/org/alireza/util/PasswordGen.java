package org.alireza.util;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

public class PasswordGen {

    public static String generateSecurePassword() {

        CharacterRule LCR = new CharacterRule(EnglishCharacterData.LowerCase);
        LCR.setNumberOfCharacters(2);

        CharacterRule UCR = new CharacterRule(EnglishCharacterData.UpperCase);
        UCR.setNumberOfCharacters(2);

        CharacterRule DR = new CharacterRule(EnglishCharacterData.Digit);
        DR.setNumberOfCharacters(2);

        CharacterRule SR = new CharacterRule(EnglishCharacterData.Special);
        SR.setNumberOfCharacters(2);

        PasswordGenerator passGen = new PasswordGenerator();

        return passGen.generatePassword(8, SR, LCR, UCR, DR);
    }
}
