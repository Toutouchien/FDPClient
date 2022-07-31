package net.skiddermc.fdpclient.features.module.modules.combat.criticals.semi

import net.skiddermc.fdpclient.event.AttackEvent
import net.skiddermc.fdpclient.features.module.modules.combat.criticals.CriticalMode

class MatrixSemi : CriticalMode("MatrixSemi") {
    private var attacks = 0
    override fun onEnable() {
        attacks = 0
    }
    override fun onAttack(event: AttackEvent) {
        attacks++
        if (attacks > 3) {
            critical.sendCriticalPacket(yOffset = 0.0825080378093, ground = false)
            critical.sendCriticalPacket(yOffset = 0.023243243674, ground = false)
            critical.sendCriticalPacket(yOffset = 0.0215634532004, ground = false)
            critical.sendCriticalPacket(yOffset = 0.00150000001304, ground = false)
            attacks = 0
        } else {
            critical.antiDesync = false
        }
    }
}