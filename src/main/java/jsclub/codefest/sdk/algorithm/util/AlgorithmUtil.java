package jsclub.codefest.sdk.algorithm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class AlgorithmUtil {
	public static Map<Node, Stack<Node>> getPathToAllFood(Bomberman ownBomPlayer, MapInfo mapInfo, boolean isNeedCheckEffectBomb, Player myPlayer) {
		Bomberman cloneBommer = Bomberman.clone(ownBomPlayer);
		List<Node> restrictNode = new ArrayList<>();
		restrictNode.addAll(cloneBommer.getWalls());
		restrictNode.addAll(cloneBommer.getBoxs());
		restrictNode.addAll(cloneBommer.getSelfisolatedZone());
		cloneBommer.setNormalHumanList(mapInfo.getNHuman());
		// if (cloneBommer.metadata.score - cloneBommer.mEnemyPlayer.score > 20 ||
		// mapInfo.boxs.size() < 10) {
		cloneBommer.listShouldEatSpoils.addAll(cloneBommer.normalHumanList);
		// }
		if (myPlayer.pill > 2) {
			cloneBommer.setVirusLists(mapInfo.getVirus(), false);
			cloneBommer.setDangerHumanList(mapInfo.getDhuman(), false);
			cloneBommer.listShouldEatSpoils.addAll(cloneBommer.getdangerHumanLists());
			cloneBommer.listShouldEatSpoils.addAll(cloneBommer.getVirusLists());
		} else {
			restrictNode.addAll(cloneBommer.getdangerHumanLists());
			restrictNode.addAll(cloneBommer.getVirusLists());
		}
		if (isNeedCheckEffectBomb) {
			restrictNode.addAll(cloneBommer.dangerBombs);
		}
		cloneBommer.setRestrictedNodes(restrictNode);
		System.out.println("list should eat " + cloneBommer.listShouldEatSpoils.size());
		Map<Node, Stack<Node>> listFood = getPathsToAllTarget(mapInfo.getMap(), cloneBommer,
				cloneBommer.listShouldEatSpoils, true);
		System.out.println("list food " + listFood.size());
		return sortByComparator(listFood, false);
	}
}
