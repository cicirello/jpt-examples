/*
 * Example programs for JavaPermutationTools library.
 * Copyright (C) 2018-2021  Vincent A. Cicirello
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

/**
 * Module declaration for the example programs for JavaPermutationTools.
 *
 * @author <a href=https://www.cicirello.org/ target=_top>Vincent A. Cicirello</a>, 
 * <a href=https://www.cicirello.org/ target=_top>https://www.cicirello.org/</a>
 */
module org.cicirello.examples.jpt {
	exports org.cicirello.examples.jpt;
	exports org.cicirello.replication.arxiv2019may;
	exports org.cicirello.replication.bict2019;
	exports org.cicirello.replication.flairs2013;
	exports org.cicirello.replication.ieeetevc2016;
	requires org.cicirello.jpt;
	requires java.management;
}
