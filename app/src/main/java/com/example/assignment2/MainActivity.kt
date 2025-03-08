package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val BORROW_REQUEST_CODE = 1
    private val TAG = "MainActivity"

    // Rental items list stored in MainActivity
    private val rentalItems = listOf(
        RentalItem(1, R.drawable.guitar, "Electric Guitar", 4.5f, listOf("With Amp", "Extra Strings"), 50),
        RentalItem(2, R.drawable.keyboard, "Keyboard", 4.2f, listOf("Sustain Pedal", "Bench"), 40),
        RentalItem(3, R.drawable.drum, "Drum Set", 5.0f, listOf("Sticks Included", "Cymbals"), 60),
        RentalItem(4, R.drawable.cello, "Cello", 4.5f, listOf("Sticks Included", "Bench"), 120)
    )

    private var currentIndex = 0 // Track current item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: MainActivity started")

        // Restore index after rotation
        currentIndex = savedInstanceState?.getInt("currentIndex") ?: 0

        val borrowButton: Button = findViewById(R.id.borrowButton)
        val nextButton: Button = findViewById(R.id.nextButton)
        val itemImage: ImageView = findViewById(R.id.instrumentImage)
        val itemID: TextView = findViewById(R.id.itemID)
        val itemName: TextView = findViewById(R.id.instrumentName)
        val itemRating: RatingBar = findViewById(R.id.instrumentRate)
        val itemPrice: TextView = findViewById(R.id.instrumentPrice)
        val attributeGroup: RadioGroup = findViewById(R.id.instrumentAttributeGroup)

        // Function to update UI
        fun updateUI() {
            val currentItem = rentalItems[currentIndex]
            itemImage.setImageResource(currentItem.imageRes)
            itemID.text = currentItem.ID.toString()
            itemName.text = currentItem.name
            itemRating.rating = currentItem.rating
            itemPrice.text = "Price: ${currentItem.price} credits"

            // Populate attributes dynamically
            attributeGroup.removeAllViews()
            for (attribute in currentItem.attributes) {
                val radioButton = RadioButton(this)
                radioButton.text = attribute
                attributeGroup.addView(radioButton)
            }
        }

        // Load UI on startup
        updateUI()

        // Move to the next item when "Next" is clicked
        nextButton.setOnClickListener {
            Log.d(TAG, "Next button clicked")
            currentIndex = (currentIndex + 1) % rentalItems.size
            updateUI()
        }

        // Handle "Borrow" button
        borrowButton.setOnClickListener {
            val intent = Intent(this, BorrowActivity::class.java)
            Log.d(TAG, "Borrow button clicked")
            val selectedRadioButton = findViewById<RadioButton>(attributeGroup.checkedRadioButtonId)
            val selectedAttribute = selectedRadioButton?.text?.toString() ?: "No attribute selected"

            intent.putExtra("rentalItem", rentalItems[currentIndex])
            intent.putExtra("selectedAttribute", selectedAttribute)
            startActivityForResult(intent, BORROW_REQUEST_CODE)
        }
    }

    // Save currentIndex before rotation
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentIndex", currentIndex)
    }

    // Handle result from BorrowActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult: requestCode=$requestCode, resultCode=$resultCode")

        if (requestCode == BORROW_REQUEST_CODE && resultCode == RESULT_OK) {
            val savedItem = data?.getParcelableExtra<RentalItem>("savedItem")
            Log.d(TAG, "Item saved: ${savedItem?.name}")
            Toast.makeText(this, "${savedItem?.name} saved!", Toast.LENGTH_SHORT).show()
        } else {
            Log.d(TAG, "Borrowing action canceled or failed")
        }
    }
}
