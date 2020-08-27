/*
 * Copyright © 2020, Simplexion, Hungary and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package zakadabar.template.dto

import kotlinx.serialization.Serializable
import zakadabar.stack.data.entity.DtoWithEntityCompanion
import zakadabar.stack.data.entity.EntityDto
import zakadabar.stack.data.schema.DtoSchema
import zakadabar.stack.extend.DtoWithEntityContract
import zakadabar.template.Template

@Serializable
data class TemplateEntityDto(

    override val id: Long,
    override val entityDto: EntityDto?,

    val name: String,
    val templateField1: String,
    val templateField2: String,

    ) : DtoWithEntityContract<TemplateEntityDto> {

    companion object : DtoWithEntityCompanion<TemplateEntityDto>() {
        override val type = "${Template.shid}/template-entity"
    }

    override fun schema() = DtoSchema.build {
        + ::name min 1 max 100 // this comes from stack entity name limit
        + ::templateField1 min 1 max 100
        + ::templateField2 min 2 max 50
    }

    override fun getType() = type

    override fun comm() = comm

}