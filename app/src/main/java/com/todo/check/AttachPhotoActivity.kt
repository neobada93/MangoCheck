package com.todo.check

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.provider.MediaStore
import android.provider.Settings
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.todo.check.widget.WebAppChromeClient
import java.io.File


abstract class AttachPhotoActivity : WebAppBaseActivity() {

    var filePathCallbackNormal: ValueCallback<Uri>? = null
    var filePathCallbackLollipop: ValueCallback<Array<Uri>>? = null
    val FILECHOOSER_NORMAL_REQ_CODE = 2001
    val FILECHOOSER_LOLLIPOP_REQ_CODE = 2002
    private var cameraImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webAppView.webChromeClient = object : WebAppChromeClient(this) {

            open

            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>?,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                //return super.onShowFileChooser(webView, filePathCallback, fileChooserParams)

                // Callback 초기화 (중요!)
                if (filePathCallbackLollipop != null) {
                    filePathCallbackLollipop!!.onReceiveValue(null)
                    filePathCallbackLollipop = null
                }
                filePathCallbackLollipop = filePathCallback

                //val isCapture = fileChooserParams!!.isCaptureEnabled
                //runCamera(isCapture)
                checkPermission()
                return true;
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        var data = data
        when (requestCode) {
            FILECHOOSER_NORMAL_REQ_CODE -> if (resultCode == Activity.RESULT_OK) {
                if (filePathCallbackNormal == null) return
                val result =
                    if (data == null || resultCode != Activity.RESULT_OK) null else data.data
                //  onReceiveValue 로 파일을 전송한다.
                filePathCallbackNormal!!.onReceiveValue(result)
                filePathCallbackNormal = null
            }
            FILECHOOSER_LOLLIPOP_REQ_CODE -> if (resultCode == Activity.RESULT_OK) {
                if (filePathCallbackLollipop == null) return
                /*
                if (data.data == null) data.data = cameraImageUri
                filePathCallbackLollipop!!.onReceiveValue(
                    WebChromeClient.FileChooserParams.parseResult(
                        resultCode,
                        data
                    )
                )
                */
                var params: Array<Uri>? = null
                if (data?.clipData != null) { // handle multiple-selected files
                    val list = mutableListOf<Uri>()
                    val numSelectedFiles = data.clipData!!.itemCount
                    for (i in 0 until numSelectedFiles) {
                        list.add(data.clipData!!.getItemAt(i).uri)
                    }
                    params = list.toTypedArray()
                } else if (data?.data != null) { // handle single-selected file
                    params = WebChromeClient.FileChooserParams.parseResult(resultCode, data)
                } else {  // 이미지 없음 꺼져
                    if (data == null) data = Intent()
                    if (data.data == null) data.data = cameraImageUri
                    params = WebChromeClient.FileChooserParams.parseResult(resultCode, data)
                }
                filePathCallbackLollipop!!.onReceiveValue(params)
                filePathCallbackLollipop = null
            } else {
                if (filePathCallbackLollipop != null) {
                    //  resultCode에 RESULT_OK가 들어오지 않으면 null 처리하지 한다.(이렇게 하지 않으면 다음부터 input 태그를 클릭해도 반응하지 않음)
                    filePathCallbackLollipop!!.onReceiveValue(null)
                    filePathCallbackLollipop = null
                }
                if (filePathCallbackNormal != null) {
                    filePathCallbackNormal!!.onReceiveValue(null)
                    filePathCallbackNormal = null
                }
            }
            else -> {
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    // 카메라 기능 구현
    private fun runCamera(_isCapture: Boolean) {
        val ts = System.currentTimeMillis().toString()
        val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        val path: File = filesDir
        val file = File(path, "mango_$ts.png") // sample.png 는 카메라로 찍었을 때 저장될 파일명이므로 사용자 마음대로
        // File 객체의 URI 를 얻는다.
        cameraImageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val strpa = applicationContext.packageName
            FileProvider.getUriForFile(this, "$strpa.fileprovider", file)
        } else {
            Uri.fromFile(file)
        }
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, cameraImageUri)
        if (!_isCapture) { // 선택팝업 카메라, 갤러리 둘다 띄우고 싶을 때

            val pickIntent = Intent(Intent.ACTION_PICK)
            pickIntent.type = MediaStore.Images.Media.CONTENT_TYPE
            pickIntent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            pickIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            val pickTitle = "사진 가져올 방법을 선택하세요."
            val chooserIntent = Intent.createChooser(pickIntent, pickTitle)

            // 카메라 intent 포함시키기..
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf<Parcelable>(intentCamera))
            startActivityForResult(chooserIntent, FILECHOOSER_LOLLIPOP_REQ_CODE)
        } else { // 바로 카메라 실행..
            startActivityForResult(intentCamera, FILECHOOSER_LOLLIPOP_REQ_CODE)
        }
    }


    fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1)
        } else {
            runCamera(false)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode === 1) {
            if (grantResults.isNotEmpty()) {
                for (element in grantResults) {
                    if (element === PackageManager.PERMISSION_DENIED) {
                        // 카메라, 저장소 중 하나라도 거부한다면 앱실행 불가 메세지 띄움
                        AlertDialog.Builder(this).setTitle("알림")
                            .setMessage("권한을 허용해주셔야 앱을 이용할 수 있습니다.")
                            .setPositiveButton(
                                "종료"
                            ) { dialog, _ ->
                                dialog.dismiss()
                                finish()
                            }.setNegativeButton(
                                "권한 설정"
                            ) { dialog, _ ->
                                dialog.dismiss()
                                val intent: Intent =
                                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                        .setData(Uri.parse("package:" + applicationContext.packageName))
                                applicationContext.startActivity(intent)
                            }.setCancelable(false).show()
                        return
                    }
                }
                runCamera(false)
            }
        }
    }

}
