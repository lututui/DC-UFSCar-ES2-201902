package org.jabref.logic.integrity;

import java.util.Optional;

import org.jabref.logic.bibtexkeypattern.BibtexKeyGenerator;
import org.jabref.logic.l10n.Localization;
import org.jabref.model.strings.StringUtil;

/**
 * Makes sure the key is legal
 */
public class ValidBibtexKeyChecker implements ValueChecker {

    private final boolean enforceLegalKey;

    public ValidBibtexKeyChecker(boolean enforceLegalKey) {
        this.enforceLegalKey = enforceLegalKey;
    }

    @Override
    public Optional<String> checkValue(String value) {
        if (StringUtil.isNullOrEmpty(value)) {
            return Optional.of(Localization.lang("empty BibTeX key"));
        }

        if (value.length()<2) {
            return Optional.of(Localization.lang("BibTeX key to small"));
        }


        if (!Character.isLetter(value.charAt(0))) {
            return Optional.of(Localization.lang("BibteX invalid (first char is not a letter)"));
        }

        String cleaned = BibtexKeyGenerator.cleanKey(value, enforceLegalKey);

        if (cleaned.equals(value)) {
            return Optional.empty();
        } else {
            return Optional.of(Localization.lang("Invalid BibTeX key"));
        }
    }
}
