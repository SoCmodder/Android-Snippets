/**
 * This class support navigate between the screens
 */
public abstract class Navigator
{
    public static void openWelcome(Activity context)
    {
        if (null == context)
        {
            return;
        }
        Intent intent = new Intent(context, WelcomeActivity.class);
        AnimationUtils.startAnimation(context, intent);
    }

    public static void openHelp(Activity context)
    {
        openUrlInBrowser(context, "https://www.xyz.info/help");
    }

    public static void openUrlInBrowser(Activity context, String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        AnimationUtils.startAnimation(context, intent);
    }
}
