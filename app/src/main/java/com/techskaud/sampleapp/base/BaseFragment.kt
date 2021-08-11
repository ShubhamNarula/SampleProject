package com.example.woohoo.base



import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import com.techskaud.sampleapp.R



abstract  class BaseFragment : Fragment() {


    /**
     * Used for add Fragment, set profile image etc...
     */
    var mActivity: BaseActivity? = null

    /**
     * To show progress
     */
    var mProgressDialog: Dialog? = null


    /**
     * Called from onCreateView () Function
     */
    public abstract fun getLayoutID():Int

    /**
     * Called from onViewCreated () Function
     */
    public abstract fun onCreateView();


    /**************************************  Fragment Lifecycle Methods  ************************************************************************************/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity as BaseActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(getLayoutID(), container, false)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreateView()

    }



    /**************************************  Fragment Lifecycle Methods  ************************************************************************************/


    /**
     * Immediately go back like click on back arrow icon or click on close button or click on cross icon
     */
    protected fun goBack() {
        activity?.onBackPressed()
    }
    /**************************************** Go Back *******************************************************************************************************/

    /********************************************* Show progress and hide progress ****************************************************************************************************/

    fun showProgress() {
        if (mProgressDialog == null) {
            mProgressDialog = Dialog(mActivity!!, android.R.style.Theme_Translucent)
            mProgressDialog?.window!!.requestFeature(Window.FEATURE_NO_TITLE)
            mProgressDialog?.setContentView(R.layout.loader_half__layout)
            mProgressDialog?.setCancelable(false)
        }

        mProgressDialog?.show()
    }

    fun hideProgress() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog?.dismiss()
        }
    }







}