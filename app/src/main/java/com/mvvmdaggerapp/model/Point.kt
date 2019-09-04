package com.mvvmdaggerapp.model

import com.google.gson.JsonObject

class Point {

    var id: String? = null

    var pointType: String? = null

    var numAwarded: Int = 0

    var selfSelected: Boolean = false

    var description: String? = null

    var allowMultiple: Boolean = false

    var type: String? = null

    var enabled: Boolean = false

    var points: Int = 0

    constructor(json: JsonObject) {
        if (json.has(JsonKey.ID) && !json.get(JsonKey.ID).isJsonNull) {
            this.id = json.get(JsonKey.ID).asString
        }

        if (json.has(JsonKey.TYPE) && !json.get(JsonKey.TYPE).isJsonNull) {
            this.type = json.get(JsonKey.TYPE).asString
        }

        if(json.has(JsonKey.ATTRIBUTES)) {
            val attributes =  json.get(JsonKey.ATTRIBUTES).asJsonObject
            if (attributes.has(JsonKey.POINTS) && !attributes.get(JsonKey.POINTS).isJsonNull) {
                this.points = attributes.get(JsonKey.POINTS).asInt
            }

            if (attributes.has(JsonKey.POINT_TYPE) && !attributes.get(JsonKey.POINT_TYPE).isJsonNull) {
                this.pointType = attributes.get(JsonKey.POINT_TYPE).asString
            }

            if (attributes.has(JsonKey.DESCRIPTION) && !attributes.get(JsonKey.DESCRIPTION).isJsonNull) {
                this.description = attributes.get(JsonKey.DESCRIPTION).asString
            }

            if (attributes.has(JsonKey.SELF_SELECTED) && !attributes.get(JsonKey.SELF_SELECTED).isJsonNull) {
                this.selfSelected = attributes.get(JsonKey.SELF_SELECTED).asBoolean
            }

            if (attributes.has(JsonKey.NUM_AWARDED) && !attributes.get(JsonKey.NUM_AWARDED).isJsonNull) {
                this.numAwarded = attributes.get(JsonKey.NUM_AWARDED).asInt
            }

            if (attributes.has(JsonKey.ENABLED) && !attributes.get(JsonKey.ENABLED).isJsonNull) {
                this.enabled = attributes.get(JsonKey.ENABLED).asBoolean
            }

            if (attributes.has(JsonKey.ALLOW_MULTIPLE) && !attributes.get(JsonKey.ALLOW_MULTIPLE).isJsonNull) {
                this.allowMultiple = attributes.get(JsonKey.ALLOW_MULTIPLE).asBoolean
            }

        }
    }

    fun toJson(): JsonObject {
        val jsonObject = JsonObject()
        jsonObject.addProperty(JsonKey.POINT_TYPE, pointType)
        jsonObject.addProperty(JsonKey.NUM_AWARDED, numAwarded)
        jsonObject.addProperty(JsonKey.SELF_SELECTED, selfSelected)
        jsonObject.addProperty(JsonKey.DESCRIPTION, description)
        jsonObject.addProperty(JsonKey.ALLOW_MULTIPLE, allowMultiple)
        jsonObject.addProperty(JsonKey.ENABLED, enabled)
        jsonObject.addProperty(JsonKey.POINTS, points)

        val json = JsonObject()
        json.add(JsonKey.ATTRIBUTES, jsonObject)
        json.addProperty(JsonKey.ID, id)
        json.addProperty(JsonKey.TYPE, type)
        return json
    }

    interface JsonKey {
        companion object {
            val ID = "id"
            val TYPE = "type"

            val POINT_TYPE = "pointType"
            val NUM_AWARDED = "num_awarded"
            val SELF_SELECTED = "self_selected"
            val DESCRIPTION = "description"
            val ALLOW_MULTIPLE = "allow_multiple"
            val ENABLED = "enabled"
            val POINTS = "points"
            val ATTRIBUTES = "attributes"

        }
    }

}