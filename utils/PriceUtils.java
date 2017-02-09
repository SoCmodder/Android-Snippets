package info.trustify.www.utils;

import javax.inject.Inject;
import javax.inject.Singleton;

import info.trustify.www.data.DataStore;
import info.trustify.www.network.model.misc.AppSettingResponse;

@Singleton
public class PriceUtils
{
    private final DataStore dataStore;
    private AppSettingResponse appSettings;

    @Inject
    public PriceUtils(DataStore dataStore)
    {
        this.dataStore = dataStore;
    }

    /**
     * Get hour rate from hours
     *
     * @param hours
     * @return
     */
    public static int getHourRate(int hours)
    {
        int hourRate;
        if (hours <= 0)
        {
            return 0;
        }
        else if (hours < 4)
        {
            hourRate = 99;
        }
        else if (hours >= 4 && hours <= 9)
        {
            hourRate = 89;
        }
        else
        {
            hourRate = 79;
        }

        return hourRate;
    }

    /**
     * Calculate total price
     *
     * @param hours
     * @return
     */
    public int amountPrice(int hours)
    {
        getAppSetting();
        int initialAmount = appSettings.getInitialAmount();

        int hourRate = getHourRate(hours);

        return initialAmount + (hours * hourRate);
    }

    /**
     * Calculate price saved
     *
     * @param hours
     * @return
     */
    public int amountPriceSaved(int hours)
    {
        return hours * 150 - amountPrice(hours);
    }

    private void getAppSetting()
    {
        if (appSettings == null && dataStore.settingsPrefs.isSet())
        {
            appSettings = dataStore.getGlobalAppSettingsPrefs();
        }
    }

    /**
     * get additional fee
     *
     * @return
     */
    public int getFee()
    {
        getAppSetting();
        return appSettings.getAdditionalHourFee();
    }
}
