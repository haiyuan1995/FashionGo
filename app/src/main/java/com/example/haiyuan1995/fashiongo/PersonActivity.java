package com.example.haiyuan1995.fashiongo;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import utils.ToastAndSnakebarUtils;

/**
 * 个人信息页面
 *
 * ,使用到第三方动态权限管理库 permissionsdispatcher
 *  //@RuntimePermissions 标记需要运行时判断的类(用于动态生成代理类)
 //@NeedsPermission 标记需要检查权限的方法
 //@OnShowRationale 授权提示回调
 //@OnPermissionDenied 授权被拒绝回调
 //@OnNeverAskAgain 授权被拒绝并不再提醒回调
 */

@RuntimePermissions
public class PersonActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.id_person_save)
    TextView idPersonSave;
    @BindView(R.id.id_person_picture)
    ImageView idPersonPicture;
    @BindView(R.id.et_person_nick)
    EditText etPersonNick;
    @BindView(R.id.et_person_sex)
    EditText etPersonSex;
    @BindView(R.id.et_person_age)
    EditText etPersonAge;
    @BindView(R.id.et_person_qq)
    EditText etPersonQq;
    @BindView(R.id.et_person_address)
    EditText etPersonAddress;
    @BindView(R.id.et_person_address_details)
    EditText etPersonAddressDetails;
    @BindView(R.id.id_person_cv_selectPicture)
    CardView idPersonCvSelectPicture;

    private static final int PHOTO_SUCCESS = 1;//从图库中获取

    private static final int CAMERA_SUCCESS = 2;//从相机中拍摄

    private static final int PHOTO_REQUEST_CUT = 3;//裁剪后返回的图片
    @BindView(R.id.id_toolbar)
    Toolbar idToolbar;

    private String picPath = "";//图片裁剪后的绝对路径

    private AlertDialog alert;

    private boolean ifSelectPicture = false;//判断是否有选择图片，默认为无

    private Uri uritempFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);
        initEvent();
        initData();
    }

    private void initEvent() {
        idToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        idToolbar.setTitle("");
        setSupportActionBar(idToolbar);

        idToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        idPersonCvSelectPicture.setOnClickListener(this);
        idPersonSave.setOnClickListener(this);
    }


    @Override
    void initData() {


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_person_cv_selectPicture:
                PersonActivityPermissionsDispatcher.showToastWithCheck(PersonActivity.this);

                break;
        }
    }



    private void SelectPicture() {
        alert = new AlertDialog.Builder(this).create();
        alert.show();
        Window window = alert.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_select_picture);
//                    window.setGravity(Gravity.BOTTOM);//设置弹窗位置
//                window.getDecorView().setPadding(0,0,0,0);//设置弹窗的padding
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            window.setAttributes(params);//调整窗口大小
            window.setWindowAnimations(R.style.dialog_select_picture_anim);

            LinearLayout idSelectFromCamera = (LinearLayout) window.findViewById(R.id.id_select_form_camera);
            idSelectFromCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getImageByCamera();
                }
            });

            LinearLayout idSelectFromPhoto = (LinearLayout) window.findViewById(R.id.id_select_form_photo);
            idSelectFromPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getImageByPhoto();
                }
            });
        }
    }

    private void getImageByPhoto() {
        //Intent getImage=new Intent(Intent.ACTION_GET_CONTENT);
        //		getImage.addCategory(Intent.CATEGORY_OPENABLE);
        //		getImage.setType("image/*");
        //旧版的获取图片uri不能在4.3以上系统使用

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, PHOTO_SUCCESS);
    }

    private void getImageByCamera() {
        Intent getImage = new Intent("android.media.action.IMAGE_CAPTURE");
        uritempFile = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"image.jpg"));
        getImage.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        startActivityForResult(getImage, CAMERA_SUCCESS);
    }

    private void cutImage(Uri imageUri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(imageUri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
//        // 裁剪后输出图片的尺寸大小
//        intent.putExtra("outputX", 300);
//        intent.putExtra("outputY",300);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);//自动尺寸

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别

        /**
         *   //intent.putExtra("return-data", true);
         *   此方法返回的图片只能是小图片（高宽350px左右的图片）
         * 故将图片保存在Uri中，调用时将Uri转换为Bitmap，此方法还可解决miui系统不能return data的问题
         */


        //uritempFile为Uri类变量，实例化uritempFile

        uritempFile =  Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"image.jpg"));
        //其他写法  uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "image.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //需要请求获取图片权限
        if (resultCode == RESULT_OK) {
            if (requestCode == PHOTO_SUCCESS) {//从图库选择
                //获取图片uri
                Uri imageUri = Uri.parse(data.getDataString());

                cutImage(imageUri);//裁剪图片

            } else if (requestCode == CAMERA_SUCCESS) {//从相机拍照保存后返回uri

                cutImage(uritempFile);//裁剪

            } else if (requestCode == PHOTO_REQUEST_CUT) {

                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uritempFile));
//                    Glide.with(PersonActivity.this).load(uritempFile).into(idPersonPicture);

                    System.out.println(uritempFile.toString());   //获取图片的绝对路径，用于上传到网上

                    idPersonPicture.setImageBitmap(bitmap);//显示图片，不能用glide，因为图片地址都原因，
                    // 而glide有缓存，会冲突图片不变化，但是实际文件已更换。
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }




    /**
     * 这里以下的代码为6.0的动态权限管理
     * **/

    @NeedsPermission(value = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE})//权限申请成功后回调该方法
    void showToast(){
        ToastAndSnakebarUtils.showToast(PersonActivity.this,"获得存储设备权限");
        SelectPicture();
    }

    @OnShowRationale({Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE})
    void showWhy(final PermissionRequest request){
        new AlertDialog.Builder(this)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();//请求权限
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage("我们需要使用您的图库和相机，如果不允许则该功能不能使用。")
                .show();
    }
    @OnPermissionDenied({Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE})//被用户拒绝了
    void denied() {
        ToastAndSnakebarUtils.showToast( PersonActivity.this,"您拒绝了权限，该功能不可用");
    }


    @OnNeverAskAgain({Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE})//用户选择了不再询问，弹窗让用户手动开启
    void neverAskAgain() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("当前应用缺少拍照权限,请去设置界面打开\n打开之后按两次返回键可回到该应用哦");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName())); // 根据包名打开对应的设置界面
                startActivity(intent);
            }
        });
        builder.create().show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PersonActivityPermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }
}
