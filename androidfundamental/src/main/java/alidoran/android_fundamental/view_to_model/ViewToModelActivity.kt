package alidoran.android_fundamental.view_to_model


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import alidoran.android_fundamental.R
import com.google.android.material.textfield.TextInputEditText
import android.util.Log
import android.view.View
import android.widget.Toast
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatEditText
import android.widget.TextView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.lang.Exception
import java.lang.reflect.Field
import java.util.*

class ViewToModelActivity : AppCompatActivity() {
    private val viewTagId = R.string.view_tag_id
    private val dictionary: MutableMap<String, String> = Hashtable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_to_model)
        val btnViewToModel = findViewById<Button>(R.id.btn_view_to_model)
        val btnModelToView = findViewById<Button>(R.id.btn_model_to_view)
        val editText = findViewById<TextInputEditText>(R.id.text_xml)
        val tagValueModel = TagValueModel("NameText", "NameTag")
        editText.tag = 1
        editText.setTag(viewTagId, tagValueModel)
        btnViewToModel.setOnClickListener {
            val testModel = setViewToModelByTag(TestModel::class.java) as TestModel?
            Toast.makeText(
                this@ViewToModelActivity,
                testModel!!.nameText + " " + testModel.nameTag,
                Toast.LENGTH_SHORT
            ).show()
        }
        btnModelToView.setOnClickListener {
            val testModel = TestModel()
            testModel.nameText = "AliDoran"
            testModel.nameTag = 2
            setModelToViewsByTag(testModel)
            Toast.makeText(
                this@ViewToModelActivity,
                editText.text.toString() + " " + editText.tag.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private val childViewByTag: Unit
        get() {
            val root = window.decorView.rootView as ViewGroup
            getChildViewByTag(root, dictionary)
        }

    private fun getChildViewByTag(v: View, entityList: MutableMap<String, String>) {
        try {
            val (tag, text) = v.getTag(viewTagId) as TagValueModel
            var key: String
            var value: String
            if (v is AppCompatEditText) {
                if (text.isNotEmpty()) {
                    key = text
                    value = v.text.toString()
                    entityList[key] = value
                }
                if (!tag.isEmpty()) {
                    key = tag
                    value = v.tag.toString()
                    entityList[key] = value
                }
            } else if (v is ViewGroup) {
                val vg = v
                for (i in 0 until vg.childCount) {
                    val child = vg.getChildAt(i)
                    getChildViewByTag(child, entityList)
                }
            }
        } catch (e: Exception) {
        }
    }

    private fun setModelToViewByTag(v: View, fieldName: String, value: Any) {
        try {
            if (v is TextView) {
                if ((v.getTag(viewTagId) as TagValueModel).text == fieldName) v.text =
                    value.toString()
                if ((v.getTag(viewTagId) as TagValueModel).tag == fieldName) v.tag = value
            } else if (v is ViewGroup) {
                val vg = v
                for (i in 0 until vg.childCount) {
                    val child = vg.getChildAt(i)
                    setModelToViewByTag(child, fieldName, value)
                }
            }
        } catch (e: Exception) {
            Log.d("SetViewError", e.toString())
        }
    }

    private fun <Model> setModelToViewsByTag(model: Model) {
        val root = window.decorView.rootView as ViewGroup
        try {
            for (field in model!!::class.java.declaredFields) {
                field.isAccessible = true
                setModelToViewByTag(root, field.name, field[model]!!)
            }
        } catch (e: Exception) {
            Log.e("Error", e.toString())
        }
    }

    private fun <T> setViewToModelByTag(type: Class<T>): Any? {
        childViewByTag
        try {
            val model = type.newInstance()
            val allFields: Array<Field> = model!!::class.java.declaredFields
            for (f in allFields) {
                if (f.type == java.lang.Long.TYPE || f.type == Integer.TYPE) {
                    f.isAccessible = true
                    f[model] = -1
                }
            }
            for ((key, value) in dictionary) {
                for (field in allFields) {
                    if (field.name == key) {
                        field.isAccessible = true
                        field[model] = getValue(field.type.toString(), value)
                        break
                    }
                }
            }
            dictionary.clear()
            return model
        } catch (e: Exception) {
            Log.e("convertDictionary", e.message!!)
            MaterialAlertDialogBuilder(this)
                .setTitle("Error")
                .setMessage(e.message)
                .show()
        }
        return null
    }

    private fun getValue(type: String, value: String): Any {
        try {
            if (type.contains("long") || type.contains("Long")) {
                return if (value.isEmpty()) -1 else value.replace(",", "").toLong()
            } else if (type.contains("int")) {
                return value.toInt()
            } else if (type.contains("float")) {
                return value.toLong()
            } else if (type.contains("boolean")) {
                return value == "true"
            }
        } catch (e: Exception) {
            Log.e("getValue", e.message!!)
        }
        return value
    }

    companion object {

        private fun getFieldName(fieldObject: Any, parent: Any): String? {
            val allFields = parent.javaClass.fields
            for (field in allFields) {
                val currentFieldObject: Any = try {
                    field[parent]
                } catch (e: Exception) {
                    return null
                }
                val isWantedField = fieldObject == currentFieldObject
                if (isWantedField) {
                    return field.name
                }
            }
            return null
        }
    }
}