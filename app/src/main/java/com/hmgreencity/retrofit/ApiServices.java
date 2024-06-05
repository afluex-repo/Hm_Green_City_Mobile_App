package com.hmgreencity.retrofit;
import com.google.gson.JsonObject;
import com.hmgreencity.model.ResponseDownlineAssociate;
import com.hmgreencity.model.request.RequestBusinessReportSearch;
import com.hmgreencity.model.request.RequestChangePassword;
import com.hmgreencity.model.request.RequestDirectSearch;
import com.hmgreencity.model.request.RequestDownlineSearch;
import com.hmgreencity.model.request.RequestKYC;
import com.hmgreencity.model.request.RequestLogin;
import com.hmgreencity.model.request.RequestPayoutLedger;
import com.hmgreencity.model.request.RequestPayoutReportSearch;
import com.hmgreencity.model.request.RequestSignup;
import com.hmgreencity.model.request.RequestTopUpList;
import com.hmgreencity.model.request.RequestUpdateProfile;
import com.hmgreencity.model.response.ResponseChangePassword;
import com.hmgreencity.model.response.ResponseDashboard;
import com.hmgreencity.model.response.ResponseDashboardTotalBusiness;
import com.hmgreencity.model.response.ResponseDownlineRank;
import com.hmgreencity.model.response.ResponseKYc;
import com.hmgreencity.model.response.ResponseLogin;
import com.hmgreencity.model.response.ResponsePinCode;
import com.hmgreencity.model.response.ResponseRegistration;
import com.hmgreencity.model.response.ResponseSignup;
import com.hmgreencity.model.response.ResponseSponsorList;
import com.hmgreencity.model.response.ResponseSponsorName;
import com.hmgreencity.model.response.ResponseUpdateProfile;
import com.hmgreencity.model.response.ResponseViewProfile;
import com.hmgreencity.model.response.responseBusinessReport.ResponseBusinessReport;
import com.hmgreencity.model.response.responseBusinessReportSearch.ResponseBusinessReportSearch;
import com.hmgreencity.model.response.responseDirect.ResponseDirect;
import com.hmgreencity.model.response.responseDirectSearch.ResponseDirectSearch;
import com.hmgreencity.model.response.responseDownline.ResponseDownline;
import com.hmgreencity.model.response.responseDownlineSearch.ResponseDownlineSearch;
import com.hmgreencity.model.response.responsePayoutLedger.ResponsePayoutLedger;
import com.hmgreencity.model.response.responsePayoutReport.ResponsePayoutReport;
import com.hmgreencity.model.response.responsePayoutReportSearch.ResponsePayoutReportSearch;
import com.hmgreencity.model.response.responseTopUpList.ResponseTopUpList;
import com.hmgreencity.model.response.responseUnpaidIncome.ResponseUnpaidIncome;
import com.hmgreencity.model.response.treeView.ResponseTreeView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiServices {

    @POST("WebAPI/login")
    Call<ResponseLogin> getLogin(@Body RequestLogin login);

    @POST("WebAPI/Registration")
    Call<ResponseSignup> Registration(@Body RequestSignup login);

    @POST("WebAPI/AssociateDashBoard")
    Call<ResponseDashboard> AssociateDashBoardTotals(@Body JsonObject login);

    @POST("WebAPI/ChangePassword")
    Call<ResponseChangePassword> ChangePassword(@Body RequestChangePassword login);

    @POST("WebAPI/TopupList")
    Call<ResponseTopUpList> TopUpLists(@Body RequestTopUpList login);
 @POST("WebAPI/TopupListNew")
    Call<ResponseTopUpList> TopUpListsNew(@Body RequestTopUpList login);

    @POST("WebAPI/ViewProfile")
    Call<ResponseViewProfile> ViewProfiles(@Body JsonObject login);

    @POST("WebAPI/PayoutReport")
    Call<ResponsePayoutReport> PayoutReports(@Body JsonObject login);

    @POST("WebAPI/PayoutReportBy")
    Call<ResponsePayoutReportSearch> getPayoutReportSearch(@Body RequestPayoutReportSearch login);

    @POST("WebAPI/UnPaidIncomes")
    Call<ResponseUnpaidIncome> UnpaidIncomes(@Body JsonObject login);

    @POST("WebAPI/PayoutLedger")
    Call<ResponsePayoutLedger> getPayoutLedgers(@Body RequestPayoutLedger login);

    @POST("/webapi/BusinessReport")
    Call<ResponseBusinessReport> GetBusinessReport(@Body JsonObject login);

    @POST("/webapi/BusinessReportBy")
    Call<ResponseBusinessReportSearch> GetBusinessReports(@Body RequestBusinessReportSearch login);

    @POST("/webapi/GetSponsorName")
    Call<ResponseSponsorName> GetSponsorName(@Body JsonObject login);

    @POST("webapi/Tree")
    Call<ResponseTreeView> getTreeView(@Body JsonObject login);

    @POST("WebAPI/AssoDashTotalBusiness")
    Call<ResponseDashboardTotalBusiness> AssoDashTotalBusiness(@Body JsonObject login);

    @POST("WebAPI/UpdateProfile")
    Call<ResponseUpdateProfile> getUpdateProfiles(@Body RequestUpdateProfile login);

    @POST("webapi/Direct")
    Call<ResponseDirect> GetResponseDirect(@Body JsonObject login);

    @POST("webapi/SearchDirect")
    Call<ResponseDirectSearch> GetResponseDirectSearch(@Body RequestDirectSearch login);

    @POST("webapi/Downline")
    Call<ResponseDownline> GetDownline(@Body JsonObject login);

    @POST("/webapi/SearchDownline")
    Call<ResponseDownlineSearch> GetDownlineSearch(@Body RequestDownlineSearch login);

    @POST("WebAPI/KYCDocuments")
    Call<ResponseKYc> UploadKyc(@Body RequestBody login);

 @POST("WebAPI/GetKYCList")
    Call<ResponseKYc> GetKYCList(@Body RequestKYC login);

    @POST("WebAPI/GetSponserList")
    Call<ResponseSponsorList>GetSponserList(@Body JsonObject login);
 @POST("WebAPI/GetStateCity")
    Call<ResponsePinCode>GetStateCity(@Body JsonObject login);
@POST("WebAPI/DownlineRegistrationAction ")
    Call<ResponseRegistration>DownlineRegistrationAction(@Body JsonObject login);
    @POST("WebAPI/DownlineRankAchieverReports")
    Call<ResponseDownlineRank>DownlineRankAchieverReports(@Body JsonObject login);

    @POST("WebAPI/DownlineRankAchieverAssociateReports")
    Call<ResponseDownlineAssociate>DownlineRankAchieverAssociateReports(@Body JsonObject login);

    @Multipart
    @POST("api/ImageUpload/user/PostUserImage")
    Call<JsonObject> uploadProfilePic(@Part("fk_UserId") RequestBody fk_UserId,
                                                 @Part() MultipartBody.Part file);

}