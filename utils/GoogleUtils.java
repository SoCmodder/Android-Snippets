public class GoogleUtils
{
    /**
     * Open app Google Place if it installed
     */
    public static void intentGooglePlay(Context context)
    {
        try
        {
            Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("<market uri>"));
            context.startActivity(mIntent);
        } catch (android.content.ActivityNotFoundException ex)
        {
            Timber.d("showPopupUpdate: " + ex.toString());
        }
    }
}
