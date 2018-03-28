/*
 * Tencent is pleased to support the open source community by making Angel available.
 *
 * Copyright (C) 2017 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 */

package com.tencent.angel.ml.classification.sparselr

import com.tencent.angel.ml.MLRunner
import org.apache.hadoop.conf.Configuration


class SparseLRRunner extends MLRunner {
  /**
    * Training job to obtain a model
    */
  override
  def train(conf: Configuration): Unit = {
    train(conf, new SparseLRModel(conf), classOf[SparseLRTrainTask])
  }

  /**
    * Incremental training job to obtain a model based on a trained model
    */
  override def incTrain(conf: Configuration): Unit = ???

  /**
    * Using a model to predict with unobserved samples
    */
  override def predict(conf: Configuration): Unit = {
    conf.setInt("angel.worker.matrix.transfer.request.timeout.ms", 60000)
    predict(conf, new SparseLRModel(conf), classOf[SparseLRPredictTask])
  }
}