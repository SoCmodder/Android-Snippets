public class SecurityUtils
{
    private static String cryptoPass = "superawesomecryptopassword";

    public static String encryptIt(String value)
    {
        try
        {
            DESKeySpec keySpec = new DESKeySpec(cryptoPass.getBytes("UTF8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);

            byte[] clearText = value.getBytes("UTF8");
            // Cipher is not thread safe
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            String encrypedValue = Base64.encodeToString(cipher.doFinal(clearText), Base64.DEFAULT);
            Timber.d("Encrypted: " + value + " -> " + encrypedValue);
            return encrypedValue;

        } catch (InvalidKeyException | IllegalBlockSizeException | NoSuchPaddingException |
                BadPaddingException | NoSuchAlgorithmException | InvalidKeySpecException | UnsupportedEncodingException e)
        {
            Timber.e(e.getMessage());
        }
        return value;
    }

    public static String decryptIt(String value)
    {
        try
        {
            DESKeySpec keySpec = new DESKeySpec(cryptoPass.getBytes("UTF8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);

            byte[] encrypedPwdBytes = Base64.decode(value, Base64.DEFAULT);
            // cipher is not thread safe
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypedValueBytes = (cipher.doFinal(encrypedPwdBytes));

            String decrypedValue = new String(decrypedValueBytes);
            Timber.d("Decrypted: " + value + " -> " + decrypedValue);
            return decrypedValue;

        } catch (InvalidKeyException e)
        {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        } catch (InvalidKeySpecException e)
        {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        } catch (BadPaddingException e)
        {
            e.printStackTrace();
        } catch (NoSuchPaddingException e)
        {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e)
        {
            e.printStackTrace();
        }
        return value;
    }

    public static String getSlug(String category)
    {
        return category.toLowerCase();
    }

    public static byte[] SHA1(String text)
    {
        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance("SHA-1");
            md.update(text.getBytes("iso-8859-1"), 0, text.length());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        return md != null ? md.digest() : new byte[0];
    }

    /**
     * Encrypt the password using our encrypt mechanism
     *
     * @param plainPassword
     * @param salt
     * @return encPass
     */
    public static String hashPassword(String plainPassword, String salt)
    {
        byte[] saltbyte = salt.getBytes();

        PBKDF2Parameters p = new PBKDF2Parameters("HmacSHA256", "UTF-8", saltbyte, 1000);
        byte[] dk = new PBKDF2Engine(p).deriveKey(plainPassword);
        System.out.println(BinTools.bin2hex(dk));
        return BinTools.bin2hex(dk).toLowerCase();
    }
}
