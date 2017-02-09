public class TextUtils
{
    public static boolean isNullOrEmpty(String source)
    {
        return source == null || source.trim().length() == 0;
    }

    public static String getPhoneNumberFormat(String format)
    {
        char[] charArray = format.toCharArray();

        if (charArray.length > 0)
        {

            StringBuilder mStringBuilder = new StringBuilder();
            for (char mChar : charArray)
            {
                if (mChar != ' ' && mChar != '(' && mChar != ')' && mChar != '-')
                {
                    mStringBuilder.append(mChar);
                }
            }

            if (mStringBuilder.toString().length() > 3)
            {
                charArray = mStringBuilder.toString().toCharArray();
                String mString = "";
                int j = 0;
                for (char mChar : charArray)
                {
                    if (j == 0)
                    {
                        mString = "(" + mChar;
                    }
                    else if (j == 3)
                    {
                        mString = mString + ") " + mChar;
                    }
                    else if (j == 6)
                    {
                        mString = mString + "-" + mChar;
                    }
                    else
                    {
                        mString += mChar;
                    }
                    j++;
                }
                return mString;

            }
        }
        return null;
    }

    public static String capFirstLetterOfWord(String initialString)
    {
        return Character.toUpperCase(initialString.charAt(0)) + initialString.substring(1).toLowerCase();
    }

    public static String capFirstLetterOfWords(String str)
    {
        String[] words = str.split(" ");
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < words.length; i++)
        {
            ret.append(Character.toUpperCase(words[i].charAt(0)));
            ret.append(words[i].substring(1));
            if (i < words.length - 1)
            {
                ret.append(' ');
            }
        }
        return ret.toString();
    }
}
