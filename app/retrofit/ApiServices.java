package com.hmgreencity.retrofit;

import com.google.gson.JsonObject;
import com.hmgreencity.model.request.RequestLogin;
import com.hmgreencity.model.request.RequestMakeFav;
import com.hmgreencity.model.request.RequestPostReview;
import com.hmgreencity.model.request.RequestSignup;
import com.hmgreencity.model.response.ResponseAboutUs;
import com.hmgreencity.model.response.ResponseAddToCart;
import com.hmgreencity.model.response.ResponseCancelOrder;
import com.hmgreencity.model.response.ResponseCartCount;
import com.hmgreencity.model.response.ResponseCityState;
import com.hmgreencity.model.response.ResponseLogin;
import com.hmgreencity.model.response.ResponseMakeFav;
import com.hmgreencity.model.response.ResponsePlaceOrder;
import com.hmgreencity.model.response.ResponsePostReview;
import com.hmgreencity.model.response.ResponseRemoveCartItem;
import com.hmgreencity.model.response.ResponseSaveAddress;
import com.hmgreencity.model.response.ResponseSendOTP;
import com.hmgreencity.model.response.categoryMenu.ResponseMenuCategory;
import com.hmgreencity.model.response.categoryWiseProducts.ResponseCategoryWiseProducts;
import com.hmgreencity.model.response.newDashboard.ResponseShopDashboardNew;
import com.hmgreencity.model.response.responseAddresses.ResponseGetAddress;
import com.hmgreencity.model.response.responseCartItems.ResponseCartItems;
import com.hmgreencity.model.response.responseOrders.ResponseOrders;
import com.hmgreencity.model.response.responseProductDetails.ResponseProductDetails;
import com.hmgreencity.model.response.responseSearch.ResponseSearch;
import com.hmgreencity.model.response.reviews.ResponseReviews;
import com.hmgreencity.model.response.termsConditions.ResponseTermsConditions;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiServices {

    @POST("APIBase/Login1")
    Call<ResponseLogin> getLogin(@Body RequestLogin jsonObject);

    @POST("APIBase/GetMenu")
    Call<ResponseMenuCategory> GetMenu();

    @POST("APIBase/SendOTP")
    Call<ResponseSendOTP> SendOTP(@Body JsonObject jsonObject);

    @POST("APIBase/VerifyOTP")
    Call<ResponseSendOTP> VerifyOTP(@Body JsonObject jsonObject);

    @POST("APIBase/Registration")
    Call<ResponseLogin> Registration(@Body RequestSignup jsonObject);

    @POST("APIBase/GetDashBoardData")
    Call<ResponseShopDashboardNew> GetDashBoardData(@Body JsonObject jsonObject);

    @POST("APIBase/QuickView")
    Call<ResponseProductDetails> getProductDetails(@Body JsonObject param);

    @POST("APIBase/AddToCart")
    Call<ResponseAddToCart> AddToCart(@Body JsonObject param);

    @POST("APIBase/CartCount")
    Call<ResponseCartCount> CartCount(@Body JsonObject param);

    @POST("APIBase/GetCategoryWiseProduct")
    Call<ResponseCategoryWiseProducts> GetCategoryWiseProduct(@Body JsonObject object);

    @POST("APIBase/GlobalSearch")
    Call<ResponseSearch> GlobalSearch(@Body JsonObject object);

    @POST("APIBase/ShowCart")
    Call<ResponseCartItems> ShowCart(@Body JsonObject param);

    @POST("APIBase/RemoveItemFromCart")
    Call<ResponseRemoveCartItem> RemoveItemFromCart(@Body JsonObject param);

    @POST("APIBase/GetAddress")
    Call<ResponseGetAddress> GetAddress(@Body JsonObject param);

    @POST("APIBase/GetStateCity")
    Call<ResponseCityState> GetStateCity(@Body JsonObject object);

    @POST("APIBase/SaveAddress")
    Call<ResponseSaveAddress> SaveAddress(@Body JsonObject param);

    @POST("APIBase/AssignProductAsFavourite")
    Call<ResponseMakeFav> AssignProductAsFavourite(@Body RequestMakeFav param);

    @POST("APIBase/GetCustomerFavouriteList")
    Call<ResponseCategoryWiseProducts> GetCustomerFavouriteList(@Body JsonObject object);

    @POST("APIBase/MyOrders")
    Call<ResponseOrders> MyOrders(@Body JsonObject param);

    @POST("APIBase/PlaceOrder")
    Call<ResponsePlaceOrder> PlaceOrder(@Body JsonObject param);

    @POST("APIBase/CancelOrder")
    Call<ResponseCancelOrder> CancelOrder(@Body JsonObject param);

    @Multipart
    @POST("api/ImageUpload/MediaUpload")
    Call<JsonObject> MediaUpload(@Part("Pk_UserId") RequestBody userId,
                                 @Part("DocumentType") RequestBody usertype,
                                 @Part() MultipartBody.Part file,
                                 @Part() MultipartBody.Part file2);

    @POST("APIBase/PostRewiew")
    Call<ResponsePostReview> PostRewiew(@Body RequestPostReview param);

    @POST("APIBase/GetReview")
    Call<ResponseReviews> GetReview(@Body JsonObject param);

    @POST("APIBase/AboutUs")
    Call<ResponseAboutUs> AboutUs();

    @POST("APIBase/ChangePassword")
    Call<ResponseCancelOrder> ChangePassword(@Body JsonObject object);

    @POST("APIBase/forgotPassword")
    Call<ResponseCancelOrder> forgotPassword(@Body JsonObject object);

    @POST("APIBase/getTermsCondition")
    Call<ResponseTermsConditions> GetTermsCondition();

    @POST("APIBase/DeleteAddress ")
    Call<ResponseCancelOrder> DeleteAddress(@Body JsonObject jsonObject);

    @POST("APIBase/UpdateAddress")
    Call<ResponseSaveAddress> UpdateAddress(@Body JsonObject param);
}