public class AnimationUtils
{
    /**
     * Start an activity with animation
     *
     * @param activity
     * @param intent
     */
    public static void startAnimation(Activity activity, Intent intent)
    {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public static void startAnimationForResult(Activity activity, Intent intent, int code)
    {
        activity.startActivityForResult(intent, code);
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    public static void fadeContentIn(ViewGroup transitionContainer)
    {
        TransitionManager.beginDelayedTransition(transitionContainer);
    }

    public static void fadeContentOut()
    {

    }
}
