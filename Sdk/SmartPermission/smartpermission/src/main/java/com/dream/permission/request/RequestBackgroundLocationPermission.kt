package com.dream.permission.request

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2023/4/19
 */
internal class RequestBackgroundLocationPermission internal constructor(permissionBuilder: PermissionBuilder):
    ChainTask {

    companion object{
        const val ACCESS_BACKGROUND_LOCATION = "android.permission.ACCESS_BACKGROUND_LOCATION"
    }

    override fun getExplainScope(): ExplainScope {
        TODO("Not yet implemented")
    }

    override fun getForwardScope(): ForwardScope {
        TODO("Not yet implemented")
    }

    override fun request() {
        TODO("Not yet implemented")
    }

    override fun requestAgain(permissions: MutableList<String>?) {
        TODO("Not yet implemented")
    }

    override fun finish() {
        TODO("Not yet implemented")
    }


}