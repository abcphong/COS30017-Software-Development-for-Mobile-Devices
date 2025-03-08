package com.example.assignment2

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
//Main class
class BorrowActivity : AppCompatActivity() {
    //Initialize
    private val MAX_CREDIT_LIMIT = 100 // Max credit limit for borrowing
    private lateinit var borrowAttributeGroup: RadioGroup
    private lateinit var itemSelectedAttribute: TextView
    private lateinit var saveButton: Button // Add this line
    private var selectedAttribute: String? = null // Store selected attribute
    private val TAG = "BorrowActivity" // Define TAG for logging

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_borrow)
        //Initialize
        Log.d(TAG, "onCreate: BorrowActivity started")
        val item = intent.getParcelableExtra<RentalItem>("rentalItem")
        val receivedAttribute = intent.getStringExtra("selectedAttribute")
        val itemImage = findViewById<ImageView>(R.id.borrowInstrumentImage)
        val itemName = findViewById<TextView>(R.id.borrowInstrumentName)
        itemSelectedAttribute = findViewById(R.id.selectedInstrumentAttribute)
        val itemPrice = findViewById<TextView>(R.id.borrowInstrumentPrice)
        saveButton = findViewById(R.id.saveButton) // Initialize saveButton
        val cancelButton = findViewById<Button>(R.id.cancelButton)
        borrowAttributeGroup = findViewById(R.id.borrowInstrumentAttributeGroup)

        saveButton.isEnabled = true
        saveButton.isClickable = true

        // check item is not null
        if (item != null) {
            Log.d(TAG, "Item received: ${item.name}, Price: ${item.price}")
            itemImage.setImageResource(item.imageRes)
            itemName.text = item.name
            itemPrice.text = "Price: ${item.price} credits"
            //check item attribute is not empty
            if (item.attributes.isNotEmpty()) {
                showAttributeOptions(item.attributes) //  Always show attributes
            }
            //check item attribute is null
            if (receivedAttribute.isNullOrEmpty()) {
                Log.d(TAG, "No attribute selected initially")
                itemSelectedAttribute.text = "Please select an attribute!"
                showAttributeOptions(item.attributes)
                saveButton.isEnabled = false
            } else {
                Log.d(TAG, "Received attribute: $receivedAttribute")
                itemSelectedAttribute.text = "Selected: $receivedAttribute"
                selectedAttribute = receivedAttribute
                saveButton.isEnabled = true // Enable save if attribute is already selected
            }
        }

        // Handle attribute selection
        borrowAttributeGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            selectedAttribute = selectedRadioButton.text.toString()
            itemSelectedAttribute.text = "Selected: $selectedAttribute"
            saveButton.isEnabled = true // Enable saving when an attribute is chosen
            Log.d(TAG, "Attribute selected: $selectedAttribute")
        }
        //Save button
        saveButton.setOnClickListener {
            Log.d(TAG, "Save button clicked")

            // Check if item is null
            if (item == null) {
                Log.e(TAG, "Error: No item selected!")
                Toast.makeText(this, "Error: No item selected!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if item is over credit
            if (item.price > MAX_CREDIT_LIMIT) {
                Log.e(TAG, "Error: Item exceeds credit limit")
                Toast.makeText(this, "Error: Cannot borrow items over $MAX_CREDIT_LIMIT credits!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if attribute is empty
            if (item.attributes.isNotEmpty() && (selectedAttribute == null || selectedAttribute == "No attribute selected")) {
                Log.e(TAG, "Error: No attribute selected")
                Toast.makeText(this, "Error: You must select an attribute!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Pass selected attribute and rental item back
            Log.d(TAG, "Saving item: ${item.name} with attribute: $selectedAttribute")
            intent.putExtra("savedItem", item)
            intent.putExtra("selectedAttribute", selectedAttribute)
            setResult(RESULT_OK, intent)
            finish()
        }
        //Save button
        cancelButton.setOnClickListener {
            Log.d(TAG, "Cancel button clicked")
            Toast.makeText(this, "Booking cancelled", Toast.LENGTH_SHORT).show()
            setResult(RESULT_CANCELED)
            finish()
        }
    }
    //Show attribute Options
    private fun showAttributeOptions(attributes: List<String>) {
        borrowAttributeGroup.removeAllViews()
        selectedAttribute = null
        Log.d(TAG, "Displaying attribute options: $attributes")
        for (attribute in attributes) {
            val radioButton = RadioButton(this)
            radioButton.text = attribute
            radioButton.id = View.generateViewId()
            borrowAttributeGroup.addView(radioButton)
        }
        //Borrow Attribute Group
        borrowAttributeGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            if (selectedRadioButton != null) {
                selectedAttribute = selectedRadioButton.text.toString()
                itemSelectedAttribute.text = "Selected: $selectedAttribute"
                Log.d(TAG, "User selected attribute: $selectedAttribute")
                saveButton.isEnabled = true // Enable button correctly
            }
        }
    }
}