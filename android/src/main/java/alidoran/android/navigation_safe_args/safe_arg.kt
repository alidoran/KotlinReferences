package alidoran.android.navigation_safe_args

/*

    *****This sample is implemented on navigation fragments as a example***

1) add (classpath "androidx.navigation:navigation-safe-args-gradle-plugin:2.5.1") to dependencies
2) add (id 'androidx.navigation.safeargs.kotlin') to plugins
3) (android.useAndroidX=true) in gradle.properties
4) ResourceManager -> created nav -> receiver fragment -> attributes -> + -> create an Arg
    A class with sender fragment name plus Directions is created
    A class with receiver fragment name plus Args is created
    A class with src action name is created inside ...directions class

Use it such below way

Sender fragment
override fun onClick(v: View) {
   val amountTv: EditText = view!!.findViewById(R.id.editTextAmount)
   val amount = amountTv.text.toString().toInt()
   val action = SpecifyAmountFragmentDirections.confirmationAction(amount)
   v.findNavController().navigate(action)
}

Receiver fragment
val args: ConfirmationFragmentArgs by navArgs()

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    val tv: TextView = view.findViewById(R.id.textViewAmount)
    val amount = args.amount
    tv.text = amount.toString()
}
 */