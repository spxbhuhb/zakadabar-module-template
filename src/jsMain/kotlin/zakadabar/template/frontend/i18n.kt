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

package zakadabar.template.frontend

import zakadabar.stack.frontend.FrontendContext.t
import zakadabar.stack.frontend.util.Dictionary
import zakadabar.template.Template
import zakadabar.template.dto.TemplateEntityDto

fun tTemplate(text: String) = t(text, Template.uuid)

val translations: Dictionary = mutableMapOf(
    "hu" to mutableMapOf(
        TemplateEntityDto.type to "Template",
    ),
    "en" to mutableMapOf(
        TemplateEntityDto.type to "Template",
    )
)
