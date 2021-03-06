/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.sos.rest.inventory;

import example.sos.rest.inventory.InventoryItem.ProductId;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Oliver Gierke
 */
public interface Inventory extends CrudRepository<InventoryItem, UUID> {

	Optional<InventoryItem> findByProductId(ProductId productId);

	default void updateInventoryItem(ProductId productId, long amount) {

		InventoryItem item = findByProductId(productId)//
				.orElseThrow(() -> new IllegalArgumentException("Unknown product!"));

		save(item.decrease(amount));
	}
}
