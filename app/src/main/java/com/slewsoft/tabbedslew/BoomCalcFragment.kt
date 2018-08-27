package com.slewsoft.tabbedslew

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.constraint.Group
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*


/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * [BoomCalcFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [BoomCalcFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BoomCalcFragment : Fragment(), View.OnClickListener {
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null
    private var mView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_calc -> calculate()
            R.id.userSettingBtn -> editUserSettings()
            R.id.btn_reset -> reset()
            R.id.jib_adj_cb -> jibAdjustChecked()
            R.id.insert_adj_cb -> insertAdjustChecked()

            else -> {
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_boom_calc, container, false)

        view.findViewById<Button>(R.id.btn_calc).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_reset).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.userSettingBtn).setOnClickListener(this)
        view.findViewById<CheckBox>(R.id.jib_adj_cb).setOnClickListener(this)
        view.findViewById<CheckBox>(R.id.insert_adj_cb).setOnClickListener(this)
        mView = view
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        mView = view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mView = null
    }

    override fun onResume() {
        super.onResume()

        // Refresh the state of the +1 button each time the activity receives focus.
//        mPlusOneButton!!.initialize(PLUS_ONE_URL, PLUS_ONE_REQUEST_CODE)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

//    override fun onAttach(context: Context?) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            mListener = context
//        } else {
//            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BoomCalcFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): BoomCalcFragment {
            val fragment = BoomCalcFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    private fun toast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
    }

    private fun jibAdjustChecked() {
        val checkBox = mView!!.findViewById<CheckBox>(R.id.jib_adj_cb)
        val group = mView!!.findViewById<Group>(R.id.jib_grp)

        if (checkBox.isChecked()) {
            group.visibility = View.GONE
            group.visibility = View.VISIBLE
        } else {
            group.visibility = View.GONE
            group.visibility = View.INVISIBLE
        }
    }

    private fun insertAdjustChecked() {
        val checkBox = mView!!.findViewById<CheckBox>(R.id.insert_adj_cb)
        val group = mView!!.findViewById<Group>(R.id.insert_grp)

        if (checkBox.isChecked()) {
            group.visibility = View.GONE
            group.visibility = View.VISIBLE
        } else {
            group.visibility = View.GONE
            group.visibility = View.INVISIBLE
        }
    }

    private fun getPreferenceInt(id: Int, defaultVal: Int): Int {
//        val preference = this?.getPreferences(Context.MODE_PRIVATE) ?: return defaultVal
        val preference = PreferenceManager.getDefaultSharedPreferences(activity)
        return preference.getInt(getString(id), defaultVal)
    }

    private fun toDegree(radians: Double): Double {
        return radians * (180 / Math.PI); }

    private fun toRadian(degree: Double): Double {
        return degree * (Math.PI / 180); }

    private fun toTwoDecimal(num: Double): Double {
        return String.format("%.2f", num).toDouble()
    }

    private fun getInputVal(id: Int): Double {
        val txt = mView!!.findViewById<EditText>(id)?.text ?: "0.0"
//        val editTxt = getView()?.findViewById<EditText>(id)?.text ?: "0.0"
        return txt.toString().toDouble()
    }

    private fun reset() {
        mView!!.findViewById<EditText>(R.id.boom_length).setText("0")
        mView!!.findViewById<EditText>(R.id.bldg_heigth).setText("0")
        mView!!.findViewById<EditText>(R.id.bldg_offset).setText("0")

        mView!!.findViewById<EditText>(R.id.jib_len).setText("0")
        mView!!.findViewById<EditText>(R.id.jib_offset).setText("0")

        mView!!.findViewById<EditText>(R.id.insert_len).setText("0")
        mView!!.findViewById<EditText>(R.id.insert_qty).setText("0")

        mView!!.findViewById<EditText>(R.id.boom_angle).setText("0")
        mView!!.findViewById<EditText>(R.id.object_offset).setText("0")
        mView!!.findViewById<EditText>(R.id.overall_radius).setText("0")
        mView!!.findViewById<EditText>(R.id.overall_boom_len).setText("0")
    }

    private fun calculate() {
        val vertOffsetVal = getPreferenceInt(R.string.hinge_pin_vert_offset, 0)
        val horizOffsetVal = getPreferenceInt(R.string.hinge_pin_horiz_offset, 0)
        val boomLen = getInputVal(R.id.boom_length)
        val bldHeight = getInputVal(R.id.bldg_heigth)
        val bldOffset = getInputVal(R.id.bldg_offset) + horizOffsetVal
        val adjBldHeight = bldHeight - vertOffsetVal
        val boomAngle = toTwoDecimal(Math.atan(adjBldHeight / bldOffset) * 180 / Math.PI)
        val hypot = Math.hypot(bldOffset, adjBldHeight)
        val objectOffSet = toTwoDecimal(bldOffset * (boomLen - hypot) / hypot)
        val jibOffsetAngle = Math.abs(boomAngle - getInputVal(R.id.jib_offset))
        val jibOffsetLen = Math.cos(toRadian(jibOffsetAngle)) * getInputVal(R.id.jib_len)
        val overallRadius = toTwoDecimal(bldOffset + objectOffSet + jibOffsetLen - horizOffsetVal)
        val overallBoomLen = toTwoDecimal(boomLen + (getInputVal(R.id.insert_qty) * getInputVal(R.id.insert_len)))

        println(jibOffsetLen)
        mView!!.findViewById<EditText>(R.id.object_offset).setText(objectOffSet.toString())
        mView!!.findViewById<EditText>(R.id.boom_angle).setText(boomAngle.toString())
        mView!!.findViewById<EditText>(R.id.overall_radius).setText(overallRadius.toString())
        mView!!.findViewById<EditText>(R.id.overall_boom_len).setText(overallBoomLen.toString())

        val cbGroup = mView!!.findViewById<Group>(R.id.cb_group)

        cbGroup.visibility = View.GONE
        cbGroup.visibility = View.VISIBLE
    }

    private fun editUserSettings() {
        val intent = Intent(activity, UserSetting::class.java)
        startActivity(intent)
    }

}
