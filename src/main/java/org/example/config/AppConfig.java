package org.example.config;

import org.example.repositories.Claim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppConfig {
    private static final List<Claim> claimsList = new ArrayList<>();
    private static double dailyAllowanceRate = 15.0; // domyślna stawka za dzień
    private static double mileageRate = 0.3; // domyślna stawka za km

    public static List<Claim> getClaimsList() {
        return Collections.unmodifiableList(claimsList);
    }

    public static void addClaim(Claim claim) {
        if (claim != null) {
            claimsList.add(claim);
        }
    }

    public static double getDailyAllowanceRate() {
        return dailyAllowanceRate;
    }

    public static void setDailyAllowanceRate(double rate) {
        dailyAllowanceRate = rate;
    }

    public static double getMileageRate() {
        return mileageRate;
    }

    public static void setMileageRate(double rate) {
        mileageRate = rate;
    }
}