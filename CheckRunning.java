import java.util.List;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

public class CheckRunning {
	//元のEye-Fiのパッケージ名
	private final String TARGET_PACKAGE = "target.package.name";
	private Context context = null;
		
	public CheckRunning(Context _context){
		this.context = _context;
	}
	
	//バッググランドで元のEye-Fiが動いているかチェックスタート
	public boolean isCheckRunning() {
		if ( isRunningTarget() ) {
			alertStopTarget();
			return true;
		}
		
		return false;
	};
		
	//バッググランドで元のEye-Fiが動いているかチェックする
	@SuppressWarnings("static-access")
	private boolean isRunningTarget(){    	    	
    	ActivityManager activityManager = ((ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE));
    	List<ActivityManager.RunningAppProcessInfo> runningAppInfo = activityManager.getRunningAppProcesses();
    	
    	if (runningAppInfo != null) {
    		for (RunningAppProcessInfo app : runningAppInfo) {
    			try {    								
					if (app.pkgList[0].equals( TARGET_PACKAGE )) {
						return true;
					}											    				    			
    			} catch (Exception e) {
    				Log.e("ERROR", e.toString());
    			}
    		}
    	}
    	return false;
    }
    
    
	//isRunningTarget()でtrueが帰った場合ダイアログでアプリを停止するかどうか確認を取る
    private void alertStopTarget(){
    	new AlertDialog.Builder(context)
		.setTitle("Here is title")
		.setMessage("Here is message")
		.setPositiveButton("NO", null)
		.setNegativeButton("YES", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				goAppSettingPage( TARGET_PACKAGE );
        	}
        }).show();
    }
    
    
    //alertStopTarget()でYESを選択したらアプリの管理画面に飛ばして強制停止を押してもらうようにする
    private void goAppSettingPage(String packageName){
    	// packageNameは任意
    	Intent intent = new Intent();

    	if (Build.VERSION.SDK_INT > 8) {
    	    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
    	    Uri uri = Uri.fromParts("package", packageName, null);
    	    intent.setData(uri);
    	} else {
    	    String appPkgName;

    	    if (Build.VERSION.SDK_INT == 8) {
    	        appPkgName = "pkg";
    	    } else {
    	        appPkgName = "com.android.settings.ApplicationPkgName";
    	    }

    	    intent.setAction(Intent.ACTION_VIEW);
    	    intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
    	    intent.putExtra(appPkgName, packageName);
    	}

    	context.startActivity(intent);
    }
}
