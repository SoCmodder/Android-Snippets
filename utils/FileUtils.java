
public class FileUtils
{
    public static final int FILE_TYPE_VID = 1337123;
    public static final int FILE_TYPE_DOC = 1337124;
    public static final int FILE_TYPE_PIC = 1337125;
    public static final int FILE_TYPE_MISSING = 1337126;

    public static String getFilePathFromUri(Context context, Uri contentUri)
    {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                res = cursor.getString(column_index);
            }
            cursor.close();
        }
        return res;
    }

    // Send image to the other app and delete on case finish
    public static String saveToInternalStorage(Application app, Bitmap bitmapImage)
    {
        ContextWrapper cw = new ContextWrapper(app);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, "profile.jpg");

        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (fos != null)
                {
                    fos.close();
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    public static File createGalleryImageFile(Context context, String suffix) throws IOException
    {

        // Create a unique image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "image" + "_" + timeStamp + suffix;

        File dir = getPublicImageStorageDir();
        if (ensureDir(dir))
        {
            File file = new File(dir.getPath(), imageFileName);
            file.createNewFile();
            return file;
        }
        else
        {
            throw new IOException("Could not create image temp file dir: " + dir.getPath());
        }

    }

    @Nullable
    public static String loadJSONFromAsset(Context activity, String fileName)
    {
        String json = null;
        try
        {
            InputStream is = activity.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e)
        {
            Timber.e(e.getMessage());
            return null;
        }
        return json;
    }

    /**
     * Get the directory in which the app stores it's images
     *
     * @return
     */
    private static File getPublicImageStorageDir()
    {
        File imageDir;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO)
        {
            imageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Trustify");
        }
        else
        {
            imageDir = new File(Environment.getExternalStorageDirectory().getPath() + "/Pictures/<Your Dir Name>");
        }
        return imageDir;
    }

    /**
     * Make sure the file exists
     *
     * @param file
     * @return
     */
    public static boolean ensureDir(File file)
    {
        file.mkdirs();
        return file.isDirectory();
    }

    public static int getAttachmentType(String url)
    {
        if (isVideo(url))
        {
            return FILE_TYPE_VID;
        }
        else if (isImage(url))
        {
            return FILE_TYPE_PIC;
        }
        else if (isDoc(url))
        {
            return FILE_TYPE_DOC;
        }
        else
        {
            return FILE_TYPE_MISSING;
        }
    }

    public static boolean isVideo(String url)
    {
        return url.endsWith(".avi") || url.endsWith(".mp4") || url.endsWith(".mov");
    }

    public static boolean isImage(String url)
    {
        return url.endsWith(".jpg") || url.endsWith(".png") || url.endsWith(".gif")
                || url.endsWith(".jpeg") || url.endsWith(".ico");
    }

    public static boolean isDoc(String url)
    {
        return url.endsWith(".doc") || url.endsWith(".docx") || url.endsWith(".pdf") || url.endsWith(".xls");
    }
}
