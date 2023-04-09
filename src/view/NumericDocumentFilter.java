package view;

import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumericDocumentFilter extends DocumentFilter {
    private final Pattern pattern;

    NumericDocumentFilter() {
        pattern = Pattern.compile("\\d*");
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws BadLocationException {
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) {
            return;
        }
        super.replace(fb, offset, length, text, attrs);
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String text, javax.swing.text.AttributeSet attrs) throws BadLocationException {
        Matcher matcher = pattern.matcher(text);
        if (!matcher.matches()) {
            return;
        }
        super.insertString(fb, offset, text, attrs);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }
}