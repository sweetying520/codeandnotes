package com.dream.permission.dialog

import android.Manifest
import android.annotation.TargetApi
import android.os.Build
import com.dream.permission.request.RequestBackgroundLocationPermission

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/19
 */


var allSpecialPermissions = setOf(
    RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION
)


/**
 * Based on this link https://developer.android.com/about/versions/10/privacy/changes#permission-groups-removed
 * Since Android Q, we can not get the permission group name by permission name anymore.
 * So we need to keep a track of relationship between permissions and permission groups on every
 * Android release since Android Q.
 */
@TargetApi(Build.VERSION_CODES.Q)
val permissionMapOnQ = mapOf(
    Manifest.permission.READ_CALENDAR to Manifest.permission_group.CALENDAR,
    Manifest.permission.WRITE_CALENDAR to Manifest.permission_group.CALENDAR,
    Manifest.permission.READ_CALL_LOG to Manifest.permission_group.CALL_LOG,
    Manifest.permission.WRITE_CALL_LOG to Manifest.permission_group.CALL_LOG,
    "android.permission.PROCESS_OUTGOING_CALLS" to Manifest.permission_group.CALL_LOG,
    Manifest.permission.CAMERA to Manifest.permission_group.CAMERA,
    Manifest.permission.READ_CONTACTS to Manifest.permission_group.CONTACTS,
    Manifest.permission.WRITE_CONTACTS to Manifest.permission_group.CONTACTS,
    Manifest.permission.GET_ACCOUNTS to Manifest.permission_group.CONTACTS,
    Manifest.permission.ACCESS_FINE_LOCATION to Manifest.permission_group.LOCATION,
    Manifest.permission.ACCESS_COARSE_LOCATION to Manifest.permission_group.LOCATION,
    Manifest.permission.ACCESS_BACKGROUND_LOCATION to Manifest.permission_group.LOCATION,
    Manifest.permission.RECORD_AUDIO to Manifest.permission_group.MICROPHONE,
    Manifest.permission.READ_PHONE_STATE to Manifest.permission_group.PHONE,
    Manifest.permission.READ_PHONE_NUMBERS to Manifest.permission_group.PHONE,
    Manifest.permission.CALL_PHONE to Manifest.permission_group.PHONE,
    Manifest.permission.ANSWER_PHONE_CALLS to Manifest.permission_group.PHONE,
    Manifest.permission.ADD_VOICEMAIL to Manifest.permission_group.PHONE,
    Manifest.permission.USE_SIP to Manifest.permission_group.PHONE,
    Manifest.permission.ACCEPT_HANDOVER to Manifest.permission_group.PHONE,
    Manifest.permission.BODY_SENSORS to Manifest.permission_group.SENSORS,
    Manifest.permission.ACTIVITY_RECOGNITION to Manifest.permission_group.ACTIVITY_RECOGNITION,
    Manifest.permission.SEND_SMS to Manifest.permission_group.SMS,
    Manifest.permission.RECEIVE_SMS to Manifest.permission_group.SMS,
    Manifest.permission.READ_SMS to Manifest.permission_group.SMS,
    Manifest.permission.RECEIVE_WAP_PUSH to Manifest.permission_group.SMS,
    Manifest.permission.RECEIVE_MMS to Manifest.permission_group.SMS,
    Manifest.permission.READ_EXTERNAL_STORAGE to Manifest.permission_group.STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE to Manifest.permission_group.STORAGE,
    Manifest.permission.ACCESS_MEDIA_LOCATION to Manifest.permission_group.STORAGE,
)

/**
 * Thankfully Android R has no permission added or removed than Android Q.
 */
@TargetApi(Build.VERSION_CODES.R)
val permissionMapOnR = mapOf(
    Manifest.permission.MANAGE_EXTERNAL_STORAGE to Manifest.permission_group.STORAGE,
).toMutableMap().apply {
    putAll(permissionMapOnQ)
}.toMap()

/**
 * Android S added 3 bluetooth related runtime permissions.
 */
@TargetApi(Build.VERSION_CODES.S)
val permissionMapOnS = mapOf(
    Manifest.permission.BLUETOOTH_SCAN to Manifest.permission_group.NEARBY_DEVICES,
    Manifest.permission.BLUETOOTH_ADVERTISE to Manifest.permission_group.NEARBY_DEVICES,
    Manifest.permission.BLUETOOTH_CONNECT to Manifest.permission_group.NEARBY_DEVICES,
).toMutableMap().apply {
    putAll(permissionMapOnR)
}.toMap()

/**
 * Android S added 3 bluetooth related runtime permissions.
 */
@TargetApi(Build.VERSION_CODES.TIRAMISU)
val permissionMapOnT = mapOf(
    Manifest.permission.READ_MEDIA_IMAGES to Manifest.permission_group.READ_MEDIA_VISUAL,
    Manifest.permission.READ_MEDIA_VIDEO to Manifest.permission_group.READ_MEDIA_VISUAL,
    Manifest.permission.READ_MEDIA_AUDIO to Manifest.permission_group.READ_MEDIA_AURAL,
    Manifest.permission.POST_NOTIFICATIONS to Manifest.permission_group.NOTIFICATIONS,
    Manifest.permission.NEARBY_WIFI_DEVICES to Manifest.permission_group.NEARBY_DEVICES,
    Manifest.permission.BODY_SENSORS_BACKGROUND to Manifest.permission_group.SENSORS,
).toMutableMap().apply {
    putAll(permissionMapOnS)
}.toMap()