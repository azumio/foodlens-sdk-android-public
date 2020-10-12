package com.azumio.android.foodlenslibrary.fragment;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u00c0\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 S2\u00020\u0001:\u0001SB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010)\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020\u000eH\u0002J\b\u0010,\u001a\u00020-H\u0002J \u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000203H\u0002J\b\u00105\u001a\u000206H\u0002J\b\u00107\u001a\u000206H\u0002J\"\u00108\u001a\u00020-2\u0006\u00109\u001a\u00020\u000e2\u0006\u0010:\u001a\u00020\u000e2\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J&\u0010=\u001a\u0004\u0018\u0001032\u0006\u0010>\u001a\u00020?2\b\u0010\u000b\u001a\u0004\u0018\u00010@2\b\u0010A\u001a\u0004\u0018\u00010BH\u0016J\b\u0010C\u001a\u00020-H\u0016J\u0010\u0010D\u001a\u00020-2\u0006\u0010E\u001a\u00020FH\u0002J\b\u0010G\u001a\u00020-H\u0016J\u001a\u0010H\u001a\u00020-2\u0006\u0010I\u001a\u0002032\b\u0010A\u001a\u0004\u0018\u00010BH\u0016J\u000e\u0010J\u001a\u00020K2\u0006\u0010;\u001a\u00020LJ\u0010\u0010M\u001a\u00020-2\u0006\u0010N\u001a\u00020FH\u0002J\b\u0010O\u001a\u00020-H\u0002J\b\u0010P\u001a\u00020-H\u0002J\n\u0010Q\u001a\u000201*\u00020RR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u0010 \u001a\n \"*\u0004\u0018\u00010!0!X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u000e\u0010\'\u001a\u00020(X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006T"}, d2 = {"Lcom/azumio/android/foodlenslibrary/fragment/CameraFragment;", "Landroidx/fragment/app/Fragment;", "()V", "broadcastManager", "Landroidx/localbroadcastmanager/content/LocalBroadcastManager;", "camera", "Landroidx/camera/core/Camera;", "cameraExecutor", "Ljava/util/concurrent/ExecutorService;", "cameraProvider", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "container", "Landroidx/constraintlayout/widget/ConstraintLayout;", "displayId", "", "displayManager", "Landroid/hardware/display/DisplayManager;", "getDisplayManager", "()Landroid/hardware/display/DisplayManager;", "displayManager$delegate", "Lkotlin/Lazy;", "imageAnalysis", "Landroidx/camera/core/ImageAnalysis;", "imageCapture", "Landroidx/camera/core/ImageCapture;", "imageResizer", "Lcom/azumio/android/foodlenslibrary/utils/ImageResizer;", "lensFacing", "outputDirectory", "Ljava/io/File;", "preview", "Landroidx/camera/core/Preview;", "selectedMeal", "", "kotlin.jvm.PlatformType", "getSelectedMeal", "()Ljava/lang/String;", "setSelectedMeal", "(Ljava/lang/String;)V", "viewFinder", "Landroidx/camera/view/PreviewView;", "aspectRatio", "width", "height", "bindCameraUseCases", "", "cropImage", "", "bitmap", "Landroid/graphics/Bitmap;", "frame", "Landroid/view/View;", "reference", "hasBackCamera", "", "hasFrontCamera", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onImageTaken", "savedUri", "Landroid/net/Uri;", "onResume", "onViewCreated", "view", "processData", "Lcom/azumio/android/foodlenslibrary/model/FoodCheckin;", "Lcom/azumio/android/foodlenslibrary/common/DataWrapper;", "setGalleryThumbnail", "uri", "setUpCamera", "updateCameraUi", "convertImageProxyToBitmap", "Landroidx/camera/core/ImageProxy;", "Companion", "foodLensLibrary_release"})
public final class CameraFragment extends androidx.fragment.app.Fragment {
    private androidx.constraintlayout.widget.ConstraintLayout container;
    private androidx.camera.view.PreviewView viewFinder;
    private java.io.File outputDirectory;
    private androidx.localbroadcastmanager.content.LocalBroadcastManager broadcastManager;
    private int displayId = -1;
    private int lensFacing = androidx.camera.core.CameraSelector.LENS_FACING_BACK;
    private androidx.camera.core.Preview preview;
    private androidx.camera.core.ImageCapture imageCapture;
    private androidx.camera.core.ImageAnalysis imageAnalysis;
    private androidx.camera.core.Camera camera;
    private androidx.camera.lifecycle.ProcessCameraProvider cameraProvider;
    private final com.azumio.android.foodlenslibrary.utils.ImageResizer imageResizer = null;
    private final kotlin.Lazy displayManager$delegate = null;
    
    /**
     * Blocking camera operations are performed using this executor
     */
    private java.util.concurrent.ExecutorService cameraExecutor;
    private java.lang.String selectedMeal;
    private static final java.lang.String TAG = "CameraFragment";
    private static final java.lang.String FILENAME = "yyyy-MM-dd-HH-mm-ss-SSS";
    private static final java.lang.String PHOTO_EXTENSION = ".jpg";
    private static final double RATIO_4_3_VALUE = 1.3333333333333333;
    private static final double RATIO_16_9_VALUE = 1.7777777777777777;
    private static final int GALLERY_IMAGE_REQUEST_CODE = 9007;
    public static final com.azumio.android.foodlenslibrary.fragment.CameraFragment.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    private final android.hardware.display.DisplayManager getDisplayManager() {
        return null;
    }
    
    public final java.lang.String getSelectedMeal() {
        return null;
    }
    
    public final void setSelectedMeal(java.lang.String p0) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void setGalleryThumbnail(android.net.Uri uri) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.azumio.android.foodlenslibrary.model.FoodCheckin processData(@org.jetbrains.annotations.NotNull()
    com.azumio.android.foodlenslibrary.common.DataWrapper data) {
        return null;
    }
    
    /**
     * Initialize CameraX, and prepare to bind the camera use cases
     */
    private final void setUpCamera() {
    }
    
    /**
     * Declare and bind preview, capture and analysis use cases
     */
    private final void bindCameraUseCases() {
    }
    
    /**
     * [androidx.camera.core.ImageAnalysisConfig] requires enum value of
     * [androidx.camera.core.AspectRatio]. Currently it has values of 4:3 & 16:9.
     *
     * Detecting the most suitable ratio for dimensions provided in @params by counting absolute
     * of preview ratio to one of the provided values.
     *
     * @param width - preview width
     * @param height - preview height
     * @return suitable aspect ratio
     */
    private final int aspectRatio(int width, int height) {
        return 0;
    }
    
    /**
     * Method used to re-draw the camera UI controls, called every time configuration changes.
     */
    private final void updateCameraUi() {
    }
    
    private final void onImageTaken(android.net.Uri savedUri) {
    }
    
    private final byte[] cropImage(android.graphics.Bitmap bitmap, android.view.View frame, android.view.View reference) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap convertImageProxyToBitmap(@org.jetbrains.annotations.NotNull()
    androidx.camera.core.ImageProxy $this$convertImageProxyToBitmap) {
        return null;
    }
    
    /**
     * Returns true if the device has an available back camera. False otherwise
     */
    private final boolean hasBackCamera() {
        return false;
    }
    
    /**
     * Returns true if the device has an available front camera. False otherwise
     */
    private final boolean hasFrontCamera() {
        return false;
    }
    
    public CameraFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/azumio/android/foodlenslibrary/fragment/CameraFragment$Companion;", "", "()V", "FILENAME", "", "GALLERY_IMAGE_REQUEST_CODE", "", "PHOTO_EXTENSION", "RATIO_16_9_VALUE", "", "RATIO_4_3_VALUE", "TAG", "createFile", "Ljava/io/File;", "baseFolder", "format", "extension", "foodLensLibrary_release"})
    public static final class Companion {
        
        /**
         * Helper function used to create a timestamped file
         */
        private final java.io.File createFile(java.io.File baseFolder, java.lang.String format, java.lang.String extension) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}