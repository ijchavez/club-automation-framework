package endpoints.household.assets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountPayloadBuilder {
    String pathClass;
    int mInitialBalance;
    Long taxStatus;
    Long accountType;
    Long mCostBasis;
    Boolean mExcludeFromRothConversions;
    Boolean mSeparatedFromService;
    Boolean mSeparatedAgePlus;
    Boolean mOverTwoYears;
    Long mTimePeriod;
    Long mDistributionPlanType;
    Long mEvery;
    String mBeginDate;
    String mEndDate;
    Boolean mCurrentlyTakingDistributions;
}
