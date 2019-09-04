package com.mvvmdaggerapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mvvmdaggerapp.model.Point
import java.util.*

class PointAdapter
    (private val listdata: ArrayList<Point>, private val context: Context) :
    RecyclerView.Adapter<PointAdapter.ViewHolder>() {

    var points = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.item_option, parent, false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val point = listdata[position]
        holder.textDesc.text = point.description
        holder.checkPoint.text = point.points.toString()
        holder.textCounter.text = point.numAwarded.toString()

        setSelected(holder.checkPoint, point)
        updataData(point)

        holder.checkPoint.setOnClickListener {
            point.selfSelected = !point.selfSelected
            setSelected(holder.checkPoint, point)
            updataData(point)
        }

        holder.imageAdd.setOnClickListener {
            if(point.selfSelected) {
                point.numAwarded += 1
                holder.textCounter.text = point.numAwarded.toString()
                points += point.points
            }
        }
        holder.imageRemove.setOnClickListener {
            if (point.numAwarded > 0 && point.selfSelected) {
                point.numAwarded -= 1
                holder.textCounter.text = point.numAwarded.toString()
                points -= point.points
            }
        }

    }

    private fun setSelected(checkPoint: CheckBox, point: Point) {
        checkPoint.isChecked = point.selfSelected
        checkPoint.setTextColor(if(point.selfSelected) ContextCompat.getColor(context, android.R.color.white)
        else ContextCompat.getColor(context, R.color.colorPrimary))

    }

    private fun updataData(attributes: Point) {
        if (attributes.selfSelected && attributes.numAwarded > 0) {
            points += (attributes.points * attributes.numAwarded)
        } else if(!attributes.selfSelected) {
            var point : Int
            if(attributes.allowMultiple && attributes.numAwarded > -1) {
                point = (attributes.points * attributes.numAwarded)
            } else {
                point = (attributes.points)
            }
            if (points > 0)
                points -= point
        }
    }


    override fun getItemCount(): Int {
        return listdata.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var checkPoint: CheckBox
        var imageAdd: ImageView
        var imageRemove: ImageView
        var textDesc: TextView
        var textCounter: TextView
        var linearLayout: LinearLayout

        init {
            checkPoint = itemView.findViewById<View>(R.id.checkPoint) as CheckBox
            checkPoint.buttonDrawable = null
            textDesc = itemView.findViewById<View>(R.id.textDesc) as TextView
            imageAdd = itemView.findViewById<View>(R.id.imageAdd) as ImageView
            imageRemove = itemView.findViewById<View>(R.id.imageRemove) as ImageView
            textCounter = itemView.findViewById<View>(R.id.textCounter) as TextView
            linearLayout = itemView.findViewById<View>(R.id.linearLayout) as LinearLayout
        }
    }
}  