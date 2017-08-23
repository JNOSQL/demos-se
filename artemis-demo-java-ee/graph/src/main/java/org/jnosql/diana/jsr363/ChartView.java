/*
 * Copyright (c) 2017 Otávio Santana and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 *
 * Contributors:
 *
 * Otavio Santana
 */

package org.jnosql.diana.jsr363;

import org.primefaces.model.chart.LineChartModel;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;

import static tec.uom.se.unit.Units.CELSIUS;
import static tec.uom.se.unit.Units.KELVIN;

@Model
public class ChartView implements Serializable {


    @Inject
    private LineChartModelBuilder builder;


    public LineChartModel getAreaModelCelsius() {
        return builder.createAreaModel(CELSIUS);
    }

    public LineChartModel getAreaModelKelvin() {
        return builder.createAreaModel(KELVIN);
    }
}