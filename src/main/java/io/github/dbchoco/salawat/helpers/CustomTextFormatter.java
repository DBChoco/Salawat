package io.github.dbchoco.salawat.helpers;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.function.UnaryOperator;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

public class CustomTextFormatter extends TextFormatter<Number>{ //Source: purring pigeon on Stackoverflow
    private static final DecimalFormat format = new DecimalFormat("#.0;-#.0");

    public CustomTextFormatter(int minDecimals, int maxDecimals) {
        super(getStringConverter(minDecimals, maxDecimals), 0, getUnaryOperator(maxDecimals, true,-1));
    }

    public CustomTextFormatter(int minDecimals, int maxDecimals, boolean allowsNegative) {
        super(getStringConverter(minDecimals, maxDecimals), 0, getUnaryOperator(maxDecimals, allowsNegative,-1));
    }

    public CustomTextFormatter(int minDecimals, int maxDecimals, boolean allowsNegative , int maxNoOfDigitsBeforeDecimal) {
        super(getStringConverter(minDecimals, maxDecimals), 0, getUnaryOperator(maxDecimals, allowsNegative, maxNoOfDigitsBeforeDecimal));
    }

    private static StringConverter<Number> getStringConverter(int minDecimals, int maxDecimals) {
        return new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                if (object == null) {
                    return "";
                }
                String format = "0.";
                for (int i = 0; i < maxDecimals; i++) {
                    if (i < minDecimals) {
                        format = format + "0";
                    } else {
                        format = format + "#";
                    }
                }
                format = format + ";-" + format;
                DecimalFormat df = new DecimalFormat(format);
                String formatted = df.format(object);
                return formatted;
            }

            @Override
            public Number fromString(String string) {
                try {
                    if (string == null) {
                        return null;
                    }
                    return format.parse(string);
                } catch (ParseException e) {
                    return null;
                }
            }
        };
    }

    private static UnaryOperator<javafx.scene.control.TextFormatter.Change> getUnaryOperator(int maxDecimals,
                                                                                             boolean allowsNegative, int noOfDigitsBeforeDecimal) {
        return new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change change) {
                if (!allowsNegative && change.getControlNewText().startsWith("-")) {
                    return null;
                }

                if (change.getControlNewText().isEmpty()) {
                    return change;
                }

                ParsePosition parsePosition = new ParsePosition(0);
                Object object = format.parse(change.getControlNewText(), parsePosition);

                if (change.getCaretPosition() == 1) {
                    if (change.getControlNewText().equals(".")) {
                        return change;
                    }
                }

                if (object == null || parsePosition.getIndex() < change.getControlNewText().length()) {
                    return null;
                } else {

                    if(noOfDigitsBeforeDecimal != -1)
                    {
                        int signum = new BigDecimal(change.getControlNewText()).signum();
                        int precision = new BigDecimal(change.getControlNewText()).precision();
                        int scale = new BigDecimal(change.getControlNewText()).scale();

                        int val = signum == 0 ? 1 : precision - scale;
                        if (val > noOfDigitsBeforeDecimal) {
                            return null;
                        }
                    }

                    int decPos = change.getControlNewText().indexOf(".");
                    if (decPos > 0) {
                        int numberOfDecimals = change.getControlNewText().substring(decPos + 1).length();
                        if (numberOfDecimals > maxDecimals) {
                            return null;
                        }
                    }
                    return change;
                }
            }
        };
    }
}
