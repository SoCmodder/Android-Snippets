public class NetworkUtils
{
    Context context;

    public static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = chain -> {
        Response originalResponse = chain.proceed(chain.request());
        if (NetworkUtils.isNetworkAvailable(context))
        {
            int maxAge = 60; // read from cache for 1 minute
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        }
        else
        {
            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    };
    private static final String NETWORK_SUCCESS_PREFS = "network_success_preferences";

    /**
     * Check connect internet
     *
     * @param mContext
     * @return boolean
     */
    public static boolean isNetworkAvailable(Context mContext)
    {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * Determines whether a request was successfull based on the HTTP response code
     *
     * @param responseCode
     * @return
     */
    public static boolean isSuccess(int responseCode)
    {
        switch (responseCode)
        {
            // OK
            case 200:
                // Created
            case 201:
                // Accepted
            case 202:
                // No Content
            case 204:
            {
                return true;
            }
            default:
                return false;
        }
    }
}
