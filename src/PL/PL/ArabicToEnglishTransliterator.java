package PL;

import com.ibm.icu.text.Transliterator;

public class ArabicToEnglishTransliterator {

    // Function to transliterate Arabic text to English
	public static String transliterateArabicToEnglish(String arabicText) {
        // Define diacritics to remove (extended set)
        String diacriticsToRemove = "[\u064E\u064F\u0650\u0652\u0651\u064B\u064C\u064D]";

        // Remove diacritics from the input text
        String cleanedText = arabicText.replaceAll(diacriticsToRemove, "");

        // Define transliteration rules from Arabic to English
        String rules = "\u0636 > d; "
                     + "\u0623 > a; "
                     + "\u0625 > i; "
                     + "\u0627 > a; "
                     + "\u0628 > b; "
                     + "\u062A > t; "
                     + "\u062B > th; "
                     + "\u062C > j; "
                     + "\u062D > h; "
                     + "\u062E > kh; "
                     + "\u062F > d; "
                     + "\u0631 > r; "
                     + "\u0632 > z; "
                     + "\u0633 > s; "
                     + "\u0634 > sh; "
                     + "\u0635 > s; "
                     + "\u0637 > t; "
                     + "\u0638 > dh; "
                     + "\u0639 > a; "
                     + "\u063A > gh; "
                     + "\u0641 > f; "
                     + "\u0642 > q; "
                     + "\u0643 > k; "
                     + "\u0644 > l; "
                     + "\u0645 > m; "
                     + "\u0646 > n; "
                     + "\u0647 > h; "
                     + "\u0648 > w; "
                     + "\u064A > y; "
                     + "\u0629 > h;"; // Taa Marbuta

        // Create a Transliterator instance with the defined rules
        Transliterator transliterator = Transliterator.createFromRules("ArabicToEnglish", rules, Transliterator.FORWARD);
        
        // Apply the transliterator to the cleaned Arabic text
        return transliterator.transliterate(cleanedText);
    }

    
}