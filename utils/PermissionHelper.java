@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class PermissionHelper
{
    public static final int PERMISSION_REQUEST_CAMERA = 1337;
    public static final int PERMISSION_REQUEST_STORAGE = 1338;
    public static final int PERMISSION_REQUEST_PHONE = 1339;
    public static final int PERMISSION_REQUEST_ALL = 1340;
    private Context context;

    // Using Dagger 2
    @Inject
    public PermissionHelper(Context context)
    {
        this.context = context;
    }

    public boolean isCameraAllowed()
    {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
    }

    public boolean isStorageAccessAllowed()
    {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED;
    }

    public boolean isPhoneAllowed()
    {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
                        == PackageManager.PERMISSION_GRANTED;
    }

    public void requestCameraPermission(Activity activity)
    {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.CAMERA},
                PERMISSION_REQUEST_CAMERA);
    }

    public void requestStoragePermission(Activity activity)
    {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_STORAGE);
    }

    public void requestPhonePermission(Activity activity)
    {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.CALL_PHONE,
                        Manifest.permission.READ_PHONE_STATE}, PERMISSION_REQUEST_PHONE);
    }

    public void requestAllPermissions(Activity activity)
    {
        ActivityCompat.requestPermissions(activity,
                new String[]
                        {
                                Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.CALL_PHONE,
                                Manifest.permission.READ_PHONE_STATE
                        },
                PERMISSION_REQUEST_ALL);
    }
}
