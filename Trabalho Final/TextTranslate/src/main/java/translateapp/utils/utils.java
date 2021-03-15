package translateapp.utils;

import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translation;

public class utils {
    //Funcao criada pelo professor
    public static String translateText(Translate translate, String text, String lsrc, String lout) {
        Translation translation = translate.translate(
                text,
                Translate.TranslateOption.sourceLanguage(lsrc),
                Translate.TranslateOption.targetLanguage(lout));
        return translation.getTranslatedText();
    }
}
