/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/SkidderMC/FDPClient/
 */
package net.skiddermc.fdpclient.features.module.modules.movement

import net.skiddermc.fdpclient.event.*
import net.skiddermc.fdpclient.features.module.EnumAutoDisableType
import net.skiddermc.fdpclient.features.module.Module
import net.skiddermc.fdpclient.features.module.ModuleCategory
import net.skiddermc.fdpclient.features.module.ModuleInfo
import net.minecraft.network.play.client.C03PacketPlayer
import net.minecraft.network.play.server.S08PacketPlayerPosLook

@ModuleInfo(name = "Freeze", category = ModuleCategory.MOVEMENT, autoDisable = EnumAutoDisableType.RESPAWN)
class Freeze : Module() {
    private var motionX = 0.0
    private var motionY = 0.0
    private var motionZ = 0.0
    private var x = 0.0
    private var y = 0.0
    private var z = 0.0

    override fun onEnable() {
        if (mc.thePlayer == null) {
            return
        }

        x = mc.thePlayer.posX
        y = mc.thePlayer.posY
        z = mc.thePlayer.posZ
        motionX = mc.thePlayer.motionX
        motionY = mc.thePlayer.motionY
        motionZ = mc.thePlayer.motionZ
    }

    @EventTarget
    fun onUpdate(event: UpdateEvent) {
        mc.thePlayer.motionX = 0.0
        mc.thePlayer.motionY = 0.0
        mc.thePlayer.motionZ = 0.0
        mc.thePlayer.setPositionAndRotation(x, y, z, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch)
    }

    @EventTarget
    fun onPacket(event: PacketEvent) {
        if (event.packet is C03PacketPlayer) {
            event.cancelEvent()
        }
        if (event.packet is S08PacketPlayerPosLook) {
            x = event.packet.x
            y = event.packet.y
            z = event.packet.z
            motionX = 0.0
            motionY = 0.0
            motionZ = 0.0
        }
    }

    override fun onDisable() {
        mc.thePlayer.motionX = motionX
        mc.thePlayer.motionY = motionY
        mc.thePlayer.motionZ = motionZ
        mc.thePlayer.setPositionAndRotation(x, y, z, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch)
    }
}